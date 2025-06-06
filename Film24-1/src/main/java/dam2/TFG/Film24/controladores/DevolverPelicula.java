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
public class DevolverPelicula {

	@Autowired
	private Film24DAO dao;

	@PostMapping("/devolverPelicula/submit")
	public String finalizarPeliculaSubmit(@RequestParam("peliculaId") int peliculaId, Model model) {

		// Obtener el usuario autenticado desde el contexto de seguridad
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal(); // tu clase personalizada
		Usuario usuario = userDetails.getUsuario(); // aquí tienes el objeto Usuario completo

		// Obtener la película por ID
		Pelicula pelicula = dao.consultarPelicula(peliculaId);

		if (usuario != null && pelicula != null) {
			// Finalizar la visualización
			dao.finalizarVisualizacion(usuario, pelicula);
			return "ConfirmacionVisualizacion"; // vista que confirma que la visualización fue finalizada
		} else {
			return "Errores.html"; // vista de error si no se encontró al usuario o la película
		}
	}
}
