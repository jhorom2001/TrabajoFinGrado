package dam2.TFG.Film24.controladores;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Resenna;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.repository.PeliculaRepository;
import dam2.TFG.Film24.repository.ResennaRepository;
import dam2.TFG.Film24.repository.UsuarioRepository;
import dam2.TFG.Film24.util.FiltrarMalasPalabras;

import org.springframework.security.core.Authentication;

@Controller
public class AltaResenna {

	@Autowired
	private ResennaRepository resennaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	@Autowired
    private FiltrarMalasPalabras filtrarMalasPalabras;

	@PostMapping("/altaResenna/submit/{peliculaId}")
	public String guardarResenna(@PathVariable("peliculaId") int peliculaId,
			@RequestParam("comentario") String comentario, @RequestParam("puntuacion") int puntuacion,
			Authentication authentication, RedirectAttributes redirectAttributes) {

		String correo = authentication.getName();
		Usuario usuario = usuarioRepository.findByCorreoElectronico(correo)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Pelicula pelicula = peliculaRepository.findById(peliculaId)
				.orElseThrow(() -> new RuntimeException("Película no encontrada"));

		Optional<Resenna> existente = resennaRepository.findByUsuarioAndPelicula(usuario, pelicula);

		if (existente.isPresent()) {
			redirectAttributes.addFlashAttribute("error",
					"Ya has enviado una reseña para la película " + pelicula.getTitulo() + ".");
			return "redirect:/detallePelicula/" + peliculaId;
		}

		
		 if (filtrarMalasPalabras.contieneMalasPalabras(comentario)) {
	            redirectAttributes.addFlashAttribute("error",
	                    "Tu opinión contiene lenguaje ofensivo. Te pedimos que la ajustes y opines nuevamente.");
	            return "redirect:/detallePelicula/" + peliculaId;
	        }
	

		Resenna resenna = new Resenna(comentario, puntuacion);
		resenna.setUsuario(usuario);
		resenna.setPelicula(pelicula);
		resennaRepository.save(resenna);

		return "redirect:/detallePelicula/" + peliculaId;
	}
}
