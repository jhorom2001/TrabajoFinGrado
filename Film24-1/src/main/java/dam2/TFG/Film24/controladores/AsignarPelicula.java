package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.security.MyUserDetails;

@Controller
public class AsignarPelicula {

	@Autowired
	private Film24DAO dao;

	// ASIGNAR PELICULA
	@GetMapping("/asignarPelicula")
	public String asignarPelicula(Model model) {
		model.addAttribute("asignarForm", new Pelicula());
		return "AsignarPelicula.html";
	}

	@PostMapping("/asignarPelicula/submit")
	public String asignarPeliculasubmit(@RequestParam("peliculaId") int peliculaId, Model model) {

		// Obtener el usuario autenticado desde el contexto de seguridad
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal(); // tu clase personalizada
		Usuario usuario = userDetails.getUsuario(); // aquí tienes el objeto Usuario completo

		// Obtener la película por ID
		Pelicula pelicula = dao.consultarPelicula(peliculaId);

		if (usuario != null && pelicula != null) {
			dao.asignarPelicula(usuario, pelicula);
			return "ConfirmacionVisualizacion";
		} else {
			return "Errores.html";
		}
	}
}
