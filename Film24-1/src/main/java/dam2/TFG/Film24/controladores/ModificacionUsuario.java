package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Usuario;

@Controller
public class ModificacionUsuario {

	@Autowired
	private Film24DAO dao;
	
	@GetMapping("usuario/modificacion")
	public String modificarUsuario(Model model) {
		model.addAttribute("usuarioForm", new Usuario());
		return "ModificacionUsuario.html";
	}
	
	@PostMapping("usuario/modificacion/submit")
	public String modificarUsuarioSubmit(Usuario usuario, Model model) {
		if(dao.consultaUsuario(usuario.getId())!=null) {
			dao.modificarUsuario(usuario);
			return "Confirmaciones.html";
		}
		else {
			return "Errores.html";
		}
		
	}
}
