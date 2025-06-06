package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.repository.PeliculaRepository;
import dam2.TFG.Film24.security.MyUserDetails;

@Controller
public class AsignarPelicula {

	@Autowired
	private Film24DAO dao;

	@Autowired
	PeliculaRepository peliculaRepository;

	// ASIGNAR PELICULA
	@GetMapping("/asignarPelicula")
	public String asignarPelicula(Model model) {
		model.addAttribute("asignarForm", new Pelicula());
		return "AsignarPelicula.html";
	}

	@PostMapping("/asignarPelicula/submit")
	public String asignarPeliculaSubmit(@RequestParam("peliculaId") int peliculaId, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
		Usuario usuario = userDetails.getUsuario();

		Pelicula pelicula = dao.consultarPelicula(peliculaId);

		if (usuario != null && pelicula != null) {
			dao.asignarPelicula(usuario, pelicula);

			// Añadir la película al modelo para mostrar en la vista
			model.addAttribute("pelicula", pelicula);

			return "redirect:/verTrailer/" + peliculaId;
		} else {
			return "Errores.html";
		}
	}

	@GetMapping("/verTrailer/{id}")
	public String verTrailer(@PathVariable("id") Integer id, Model model) {
		Pelicula pelicula = peliculaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Película no encontrada"));
		model.addAttribute("pelicula", pelicula);
		return "verTrailer";
	}
}
