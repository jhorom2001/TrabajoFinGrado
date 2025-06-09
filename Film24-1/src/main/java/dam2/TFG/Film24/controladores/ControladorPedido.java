package dam2.TFG.Film24.controladores;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.LineaPedido;
import dam2.TFG.Film24.modelo.Pedido;
import dam2.TFG.Film24.modelo.Producto;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.util.StripeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pedido")
public class ControladorPedido {

	@Autowired
	private Film24DAO dao;

	@Autowired
	private StripeService stripeService;

	@GetMapping("/finalizar")
	public String mostrarResumenPedido(HttpSession session, Model model, HttpServletRequest request) {
		List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");

		if (carrito == null || carrito.isEmpty()) {
			return "redirect:/producto/lista";
		}

		model.addAttribute("carrito", carrito);

		double total = 0;
		for (LineaPedido lp : carrito) {
			total += lp.getCantidad() * lp.getPrecioUnitario();
		}
		model.addAttribute("totalPedido", total);
		
		DecimalFormat df = new DecimalFormat("#0.00");
		String totalFormateado = df.format(total);
		session.setAttribute("totalPedidoFormateado", totalFormateado);

		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("_csrf", token);

		return "finalizar";
	}

	@PostMapping("/finalizar")
	public String procesarFinalizarCompra(HttpSession session) {
		List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
		if (carrito == null || carrito.isEmpty()) {
			return "redirect:/producto/lista";
		}
		return "redirect:/pedido/tipoDePago";
	}

	@PostMapping("/procesarPago")
	public String procesarPago(@RequestParam("paymentMethodId") String paymentMethodId, HttpSession session,
			Model model) {
		System.out.println("POST REALIZANDOSE...");

		List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

		if (carrito == null || carrito.isEmpty() || usuario == null) {
			return "redirect:/producto/lista";
		}

		double total = carrito.stream().mapToDouble(lp -> lp.getCantidad() * lp.getPrecioUnitario()).sum();

		try {
			PaymentIntent intent = stripeService.crearPaymentIntent(paymentMethodId, total);

			if ("succeeded".equals(intent.getStatus())) {
				System.out.println("Pago exitoso. Guardando pedido...");
				// Guardar pedido
				Pedido pedido = new Pedido();
				pedido.setUsuario(usuario);
				pedido.setFecha(LocalDateTime.now());
				pedido.setLineas(carrito);
				pedido.recalcularTotal();

				for (LineaPedido lp : carrito) {
					lp.setPedido(pedido);
					Producto producto = lp.getProducto();
					producto.setStock(producto.getStock() - lp.getCantidad());
					dao.actualizarProducto(producto);
				}

				dao.altaPedido(pedido);
				session.removeAttribute("carrito");

				return "confirmacionPedido";
			} else {
				System.out.println("Pago no completado. Estado: " + intent.getStatus());
				model.addAttribute("error", "El pago no se completó: " + intent.getStatus());
				return "pagoDenegado";
			}
		} catch (StripeException e) {
			System.out.println("Excepción en Stripe: " + e.getMessage());
			model.addAttribute("error", "Error al procesar el pago: " + e.getMessage());
			return "pagoDenegado";
		}
	}

	@GetMapping("/tipoDePago")
	public String mostrarTipoDePago(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("_csrf", token);
		return "tipoDePago";
	}

	@GetMapping("/confirmado")
	public String pedidoConfirmado() {
		System.out.println("Mostrando página de pedido confirmado.");
		return "confirmacionPedido";
	}

	@PostMapping("/eliminarLinea")
	public String eliminarLineaPedido(@RequestParam("idProducto") Long idProducto, HttpSession session) {
		List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
		if (carrito == null)
			return "redirect:/pedido/finalizar";

		LineaPedido lineaAEliminar = null;

		for (LineaPedido lp : carrito) {
			if (lp.getProducto().getId() == idProducto) {
				lineaAEliminar = lp;
				break;
			}

		}

		if (lineaAEliminar != null) {
			carrito.remove(lineaAEliminar);

			double total = 0;
			for (LineaPedido lp : carrito) {
				total += lp.getCantidad() * lp.getPrecioUnitario();
			}

			DecimalFormat df = new DecimalFormat("#0.00");
			String totalFormateado = df.format(total);

			session.setAttribute("carrito", carrito);
			session.setAttribute("totalPedidoFormateado", totalFormateado);
		}

		return "redirect:/pedido/finalizar";
	}

}