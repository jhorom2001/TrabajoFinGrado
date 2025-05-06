package dam2.TFG.Film24.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String getPaginaPrincipal() {
		return "index";
	}

}
