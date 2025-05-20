package dam2.TFG.Film24.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.repository.PeliculaRepository;

@Controller
public class ControladorUsuario {

	@Autowired
	private PeliculaRepository peliculaRepository;

	@GetMapping("/acercade")
	public String acercade(Model model) {
		model.addAttribute("acercadeForm", new Pelicula());
		return "acercade";
	}

	@GetMapping("/localizacion")
	public String localizacion(Model model) {
		return "localizacion";
	}

	@GetMapping("/ConfirmacionVisualizacion")
	public String confirmacionVisualizacion(Model model) {
		return "ConfirmacionVisualizacion";
	}

	@GetMapping("/buscarPorCategoria")
	public String mostrarPeliculasPorCategoria(@RequestParam(name = "categoria", required = false) String categoria,
			Model model) {
		if (categoria != null && !categoria.isEmpty()) {
			List<Pelicula> peliculasFiltradas = peliculaRepository.findByCategoria(categoria);
			model.addAttribute("peliculas", peliculasFiltradas);
		}
		return "buscarPorCategoria";
	}

	@GetMapping("/buscarPorTitulo")
	public String mostrarPeliculasPorTitulo(@RequestParam(name = "titulo", required = false) String titulo,
			Model model) {
		if (titulo != null && !titulo.isEmpty()) {
			List<Pelicula> peliculasFiltradas = peliculaRepository.findByTituloContainingIgnoreCase(titulo);
			model.addAttribute("peliculas", peliculasFiltradas);
		}
		return "buscarPorTitulo";
	}

	@GetMapping("/peliculasMasPopulares")
	public String mostrarPeliculasMasValoradas(Model model) {
		List<Pelicula> peliculasMejorValoradas = peliculaRepository.findPeliculasOrderByMediaPuntuacion();
		model.addAttribute("peliculas", peliculasMejorValoradas);
		return "peliculasMasPopulares";
	}
}
