package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Usuario;

@Controller
public class AsignarPelicula {

	@Autowired
	private Film24DAO dao;
	
	//ASIGNAR PELICULA
	@GetMapping("/pelicula/asignarPelicula")
	public String asignarPelicula(Model model) {
	    model.addAttribute("asignarForm", new Pelicula());
	    return "AsignarPelicula.html";
	}
	
	@PostMapping("/pelicula/asignarPelicula/submit")
    public String asignarPeliculasubmit(Pelicula pelicula, Model model) {
		 Usuario u=dao.consultaUsuario(pelicula.getUsuarios().get(0).getId());
		 Pelicula p=dao.consultarPelicula(pelicula.getId());
		 if (u!=null && p!= null) {
	         dao.asignarPelicula(u, p); 
	         return "Confirmaciones.html";
	     } else {
	         return "Errores.html";
	     }
	}		
}
