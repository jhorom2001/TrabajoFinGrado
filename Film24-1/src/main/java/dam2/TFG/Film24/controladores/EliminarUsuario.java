package dam2.TFG.Film24.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.dao.Film24DAO;

@Controller
public class EliminarUsuario {

	@Autowired
	private Film24DAO dao;

	@GetMapping("/eliminarUsuario")
	public String bajaUsuario(Model model) {
		List<Usuario> listaUsuarios = dao.listaUsuarios();
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "eliminarUsuario";
	}

	@PostMapping("/eliminarUsuario/submit")
	public String bajaUsuarioSubmit(Usuario usuario, Model model) {
		System.out.println(usuario.getId());
		Usuario u = dao.consultaUsuario(usuario.getId());
		if (u != null) {
			dao.eliminarUsuario(u);
			return "confirmacionEliminarUsuario.html";
		} else {
			return "Errores.html";
		}
	}

}
