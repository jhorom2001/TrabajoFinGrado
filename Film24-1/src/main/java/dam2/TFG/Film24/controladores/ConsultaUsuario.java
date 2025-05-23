package dam2.TFG.Film24.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.modelo.Visualizacion;
import dam2.TFG.Film24.repository.UsuarioRepository;
import dam2.TFG.Film24.repository.VisualizacionRepository;

@Controller
public class ConsultaUsuario {
	
//	@Autowired
//	private Film24DAO dao;
	
	@Autowired
	VisualizacionRepository visualizacionRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
//	@GetMapping("/usuario/consulta")
//	public String consultaUsuario(Model model) {
//		model.addAttribute("usuarioConsultaForm", new Usuario());
//		return "ConsultaUsuario.html";
//	}
//	
//	@PostMapping("/usuario/consulta/submit")
//	public String consultaUsuarioSubmit(Usuario usuario, Model model) {
//		return "redirect:/usuario/consulta/" + usuario.getId();
//	}
//	
//	@GetMapping("/usuario/consulta/{id}")
//	public String consultaUsuarioResultado(@PathVariable("id") int id, Model model) {
//		model.addAttribute("id", id);
//		Usuario usuario=dao.consultaUsuario(id);
//		model.addAttribute("usuarioConsultaForm", usuario==null? new Usuario(): usuario);
//		return "ConsultaUsuario.html";
//	}
	
	@GetMapping("/visualizacionesEnProgreso")
	public String verPeliculasEnProgreso(Model model) {
	    // Obtener el correo electrónico del usuario autenticado
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String correo = auth.getName();

	    // Buscar el usuario en la base de datos
	    Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreoElectronico(correo);

	    if (usuarioOpt.isPresent()) {
	        Usuario usuario = usuarioOpt.get();
	        List<Visualizacion> visualizaciones = visualizacionRepository.findByUsuarioAndEnProgresoTrue(usuario);
	        model.addAttribute("visualizaciones", visualizaciones);
	        return "visualizacionesEnProgreso"; 
	    } else {
	        return "redirect:/login?error"; 
	    }
	}

}
