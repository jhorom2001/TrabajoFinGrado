package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Usuario;

@Controller
public class ModificacionUsuario {

	@Autowired
	private Film24DAO dao;

	@GetMapping("/usuario/modificacionUsuario/{id}")
	public String modificarUsuario(@PathVariable int id, Model model) {
	    Usuario usuario = dao.consultaUsuario(id);
	    if (usuario == null) {
	        return "Errores";
	    }
	    model.addAttribute("usuario", usuario);
	    return "modificacionUsuario";
	}

	@PostMapping("/usuario/modificacionUsuario/submit")
	public String modificarUsuarioSubmit(@ModelAttribute("usuario") Usuario usuario, Model model) {
	    if (dao.consultaUsuario(usuario.getId()) != null) {
	        dao.modificarUsuario(usuario);
	        return "confirmacionModificarUsuario";
	    } else {
	        return "Errores";
	    }
	}

}