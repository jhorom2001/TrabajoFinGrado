package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;

@Controller
public class EliminarPelicula {

	@Autowired
	private Film24DAO dao;
	
	@GetMapping("/pelicula/baja")
	public String bajaPelicula(Model model) {
		model.addAttribute("peliculaForm", new Pelicula());
		return "BajaPelicula.html";
	}
	
	@PostMapping("/pelicula/baja/submit")
	public String bajaPeliculaSubmit(Pelicula pelicula, Model model) {
		Pelicula p=dao.consultarPelicula(pelicula.getId());
		if(p!=null) {
			dao.eliminarPelicula(p);
			return "Confirmaciones.html";
		}
		else {
			return "Errores.html";
		}
	}
}
