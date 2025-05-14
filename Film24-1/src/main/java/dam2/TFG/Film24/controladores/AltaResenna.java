package dam2.TFG.Film24.controladores;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Resenna;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.repository.PeliculaRepository;
import dam2.TFG.Film24.repository.ResennaRepository;
import dam2.TFG.Film24.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;

@Controller
public class AltaResenna {

	@Autowired
	private ResennaRepository resennaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PeliculaRepository peliculaRepository;

	@PostMapping("/altaResenna/submit/{peliculaId}")
	public String guardarResenna(@PathVariable("peliculaId") int peliculaId,
			@RequestParam("comentario") String comentario, @RequestParam("puntuacion") int puntuacion,
			Authentication authentication) {

		// Obtener el usuario actual
		String correo = authentication.getName();
		Usuario usuario = usuarioRepository.findByCorreoElectronico(correo)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		// Obtener la película
		Pelicula pelicula = peliculaRepository.findById(peliculaId)
				.orElseThrow(() -> new RuntimeException("Película no encontrada"));

		// Crear una nueva reseña
		Resenna resenna = new Resenna(comentario, puntuacion);
		resenna.setUsuario(usuario);
		resenna.setPelicula(pelicula);

		// Guardar la reseña
		resennaRepository.save(resenna);

		return "redirect:/detallePelicula/" + peliculaId;
	}

}