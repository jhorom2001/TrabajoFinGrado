
package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Producto;

@Controller
public class AltaProducto {

    @Autowired
    private Film24DAO dao;

    @GetMapping("/altaProducto")
    public String altaProducto(Model model) {
        model.addAttribute("productoForm", new Producto());
        return "altaProducto";
    }

    @PostMapping("/altaProducto/submit")
    public String altaProductoSubmit(Producto producto, Model model) {
        dao.altaProducto(producto);
        model.addAttribute("productoForm", producto);
        return "confirmacionAltaProducto";
    }
}
