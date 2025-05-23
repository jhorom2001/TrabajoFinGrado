package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Usuario;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@ControllerAdvice
public class ControladorUsuarioRegistrado {

	@Autowired
	private Film24DAO dao;

	@ModelAttribute
	public void agregarUsuarioAlModelo(Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
			String correo = auth.getName();

			Usuario usuario = dao.obtenerUsuarioPorCorreoElectronico(correo);
			if (usuario != null) {
				model.addAttribute("usuarioLogueado", usuario);
				session.setAttribute("usuarioLogueado", usuario);

			}
		}
	}
}
