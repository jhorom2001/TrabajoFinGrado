package dam2.TFG.Film24.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dam2.TFG.Film24.modelo.Pelicula;

@Controller
public class ControladorUsuario {
	
	@GetMapping("/acercade")
	public String acercade(Model model) {
		model.addAttribute("acercadeForm", new Pelicula());
		return "acercade";
	}
	
	@GetMapping("/localizacion")
	public String localizacion(Model model) {
		return "localizacion";
	}
	
	@GetMapping("/ConfirmacionVisualizacion")
	public String confirmacionVisualizacion(Model model) {
		return "ConfirmacionVisualizacion";
	}
}
