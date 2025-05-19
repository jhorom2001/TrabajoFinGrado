
package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import dam2.TFG.Film24.modelo.LineaPedido;
import dam2.TFG.Film24.modelo.Producto;
import dam2.TFG.Film24.dao.Film24DAO;

@Controller
@RequestMapping("/producto")
public class ConsultaProducto {

    @Autowired
    private Film24DAO dao;

    @GetMapping("/lista")
    public String listaProductos(Model model, HttpSession session) {
        List<Producto> productos = dao.listaProductos();
        model.addAttribute("productos", productos);

        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
        int cantidadTotal = 0;
        if (carrito != null) {
            cantidadTotal = carrito.stream().mapToInt(LineaPedido::getCantidad).sum();
        }
        model.addAttribute("carritoCantidad", cantidadTotal);

        return "listaProducto";
    }
    
    @PostMapping("/comprar/{id}")
    public String comprarProducto(@PathVariable Long id, HttpSession session) {
        Producto producto = dao.buscarProductoPorId(id);
        if (producto == null) {
            return "redirect:/producto/lista";
        }

        List<LineaPedido> carrito = (List<LineaPedido>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }

        boolean encontrado = false;
        for (LineaPedido lp : carrito) {
            if (lp.getProducto().getId() == producto.getId()) {
                lp.setCantidad(lp.getCantidad() + 1);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            LineaPedido linea = new LineaPedido();
            linea.setProducto(producto);
            linea.setCantidad(1);
            linea.setPrecioUnitario(producto.getPrecio());
            carrito.add(linea);
        }

        int cantidadTotal = carrito.stream().mapToInt(LineaPedido::getCantidad).sum();
        session.setAttribute("carritoCantidad", cantidadTotal);

        return "redirect:/producto/lista";
    }
}