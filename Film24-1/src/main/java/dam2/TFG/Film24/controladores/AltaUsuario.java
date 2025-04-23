package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.dao.Film24DAO;

@Controller
public class AltaUsuario {
	
	@Autowired
	private Film24DAO dao;
	
	@GetMapping("/usuario/alta")
	public String altaUsuario(Model model) {
		model.addAttribute("usuarioForm", new Usuario());
		return "altaUsuario.html";
	}
	
	@PostMapping("/usuario/alta/submit")
	public String altaClienteSubmit(Usuario usuario, Model model) {
		System.out.println("ALTA USUARIO");
		model.addAttribute("usuarioForm", usuario);
		dao.altaUsuario(usuario);
		return "Confirmaciones.html";
	}

}
