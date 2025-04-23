package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Usuario;

@Controller
public class ConsultaUsuario {
	
	@Autowired
	private Film24DAO dao;
	
	@GetMapping("/usuario/consulta")
	public String consultaUsuario(Model model) {
		model.addAttribute("usuarioConsultaForm", new Usuario());
		return "ConsultaUsuario.html";
	}
	
	@PostMapping("/usuario/consulta/submit")
	public String consultaUsuarioSubmit(Usuario usuario, Model model) {
		return "redirect:/usuario/consulta/" + usuario.getId();
	}
	
	@GetMapping("/usuario/consulta/{id}")
	public String consultaUsuarioResultado(@PathVariable("id") int id, Model model) {
		model.addAttribute("id", id);
		Usuario usuario=dao.consultaUsuario(id);
		model.addAttribute("usuarioConsultaForm", usuario==null? new Usuario(): usuario);
		return "ConsultaUsuario.html";
	}

}
