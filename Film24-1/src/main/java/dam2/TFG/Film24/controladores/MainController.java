package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dam2.TFG.Film24.repository.PeliculaRepository;

@Controller
public class MainController {

	@Autowired
	private PeliculaRepository peliculaRepository;

	@GetMapping("/")
	public String MostrarPeliculasPorCategoria(Model model) {
		model.addAttribute("listaPeliculas", peliculaRepository.findAll());

		model.addAttribute("peliculasAccion", peliculaRepository.findByCategoria("Accion"));
		model.addAttribute("peliculasAventura", peliculaRepository.findByCategoria("Aventura"));
		model.addAttribute("peliculasComedia", peliculaRepository.findByCategoria("Comedia"));
		model.addAttribute("peliculasDrama", peliculaRepository.findByCategoria("Drama"));
		model.addAttribute("peliculasCienciaFiccion", peliculaRepository.findByCategoria("Ciencia ficcion"));
		model.addAttribute("peliculasFantasia", peliculaRepository.findByCategoria("Fantasia"));
		model.addAttribute("peliculasTerror", peliculaRepository.findByCategoria("Terror"));

		return "index";
	}

}
