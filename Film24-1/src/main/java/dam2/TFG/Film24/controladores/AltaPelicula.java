package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;

@Controller
public class AltaPelicula {
	
	@Autowired
	private Film24DAO dao;
	
	@GetMapping("/pelicula/alta")
	public String altaPelicula(Model model) {
		model.addAttribute("peliculaForm", new Pelicula());
		return "altaPelicula.html";
	}
	
	@PostMapping("/pelicula/alta/submit")
	public String altaPeliculaSubmit(Pelicula pelicula, Model model) {
		System.out.println("ALTA PELICULA");
		model.addAttribute("peliculaForm", pelicula);
		dao.altaPelicula(pelicula);
		return "Confirmaciones.html";
	}

}
