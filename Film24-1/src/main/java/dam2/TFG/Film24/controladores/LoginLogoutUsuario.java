package dam2.TFG.Film24.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginLogoutUsuario {
	
	@GetMapping("/usuarioLogeado")
    public String usuarioLogeado() {
        return "usuarioLogeado";
    }

}
