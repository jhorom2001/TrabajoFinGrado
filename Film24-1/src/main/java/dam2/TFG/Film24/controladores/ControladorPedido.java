package dam2.TFG.Film24.controladores;

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

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.LineaPedido;
import dam2.TFG.Film24.modelo.Pedido;
import dam2.TFG.Film24.modelo.Producto;
import dam2.TFG.Film24.modelo.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pedido")
public class ControladorPedido {

    @Autowired
    private Film24DAO dao;

    @GetMapping("/finalizar")
    public String mostrarResumenPedido(HttpSession session, Model model, HttpServletRequest request) {
        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");

        System.out.println("Accediendo a resumen del pedido...");
        if (carrito == null) {
            System.out.println("Carrito vacío o no encontrado.");
        } else {
            System.out.println("Carrito con " + carrito.size() + " producto(s).");
        }

        model.addAttribute("carrito", carrito);

        // Calcular total
        double total = 0;
        if (carrito != null) {
            for (LineaPedido lp : carrito) {
                total += lp.getCantidad() * lp.getPrecioUnitario();
            }
        }
        model.addAttribute("totalPedido", total);

        // CSRF token para el formulario
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("_csrf", token);

        return "finalizar";
    }


    @PostMapping("/finalizar")
    public String finalizarPedido(HttpSession session) {
        System.out.println("POST /pedido/finalizar invocado");

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");

        if (usuario == null) {
            System.out.println("Usuario no logueado.");
        } else {
            System.out.println("Usuario logueado: " + usuario.getNombre());
        }

        if (carrito == null || carrito.isEmpty()) {
            System.out.println("El carrito está vacío. Redirigiendo a lista de productos.");
            return "redirect:/producto/lista";
        }

        // Crear y guardar el pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFecha(LocalDateTime.now());

        for (LineaPedido lp : carrito) {
            lp.setPedido(pedido);
        }
        pedido.setLineas(carrito);

        pedido.recalcularTotal();
        
        for (LineaPedido lp : carrito) {
            Producto producto = lp.getProducto();
            int nuevoStock = producto.getStock() - lp.getCantidad();
            producto.setStock(nuevoStock);
            dao.actualizarProducto(producto);
        }
        System.out.println("Total del pedido: " + pedido.getTotal());

        for (LineaPedido lp : carrito) {
            System.out.println("Producto: " + lp.getProducto().getNombre() +
                               ", Cantidad: " + lp.getCantidad() +
                               ", Precio Unitario: " + lp.getPrecioUnitario());
        }

        dao.altaPedido(pedido);
        System.out.println("Pedido guardado correctamente.");

        session.removeAttribute("carrito");
        System.out.println("Carrito limpiado de la sesión.");

        return "redirect:/pedido/confirmado";
    }

    //POR VER EL DE LA TIENDA
    @GetMapping("/tipoDePago")
    public String mostrarTipoDePago(Model model) {
        return "tipoDePago";
    }

    
    @GetMapping("/confirmado")
    public String pedidoConfirmado() {
        System.out.println("Mostrando página de pedido confirmado.");
        return "confirmacionPedido";
    }
    
    //Borrar productos del carrito
    
    
    
    @PostMapping("/eliminarLinea")
    public String eliminarLineaPedido(@RequestParam("idProducto") Long idProducto, HttpSession session) {
        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
        if (carrito == null)
            return "redirect:/pedido/finalizar";

        LineaPedido lineaAEliminar = null;

        for (LineaPedido lp : carrito) {
            if (lp.getProducto().getId()==idProducto) {
                lineaAEliminar = lp;
                break;
            }
        }

        if (lineaAEliminar != null) {
            carrito.remove(lineaAEliminar);
            session.setAttribute("carrito", carrito);
        }

        return "redirect:/pedido/finalizar";
    }
    
    
    
    }