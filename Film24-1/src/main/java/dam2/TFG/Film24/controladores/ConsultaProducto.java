package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dam2.TFG.Film24.dao.Film24DAO;

@Controller
public class ConsultaProducto {

    @Autowired
    private Film24DAO dao;

    @GetMapping("/producto/lista")
    public String listaProductos(Model model) {
        model.addAttribute("listaProductos", dao.listaProductos());
        return "listaProducto.html";
    }
}

