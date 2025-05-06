package dam2.TFG.Film24.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Usuario;

@Controller
public class ControladorAdmin {
	
	@Autowired
	private Film24DAO dao;
	
	@GetMapping("/loginadmin")
	public String mostrarLoginAdmin() {
	    return "loginadmin";
	}
	
	@GetMapping("/interfazAdmin")
	public String mostrarInterfazAdmin() {
	    return "interfazAdmin";
	}
	
	@GetMapping("/listaPeliculas")
    public String obtenerPeliculas(Model model) {
        List<Pelicula> listaPeliculas = dao.listaPeliculas();
        model.addAttribute("listaPeliculas", listaPeliculas);
        return "listaPeliculas";
    }
	
	@GetMapping("/listaUsuarios")
    public String obtenerUsuarios(Model model) {
        List<Usuario> listaUsuarios=dao.listaUsuarios();
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "listaUsuarios";
    }

	
}
