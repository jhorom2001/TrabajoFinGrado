package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Resenna;

@Controller
public class EliminarResenna {
	
	@Autowired
	private Film24DAO dao;

	@GetMapping("/resenna/baja")
	public String bajaResenna(Model model) {
		model.addAttribute("resennaForm", new Resenna());
		return "BajaResenna";
	}

	@PostMapping("/resenna/baja/submit")
	public String bajaResennaSubmit(Resenna resenna, Model model) {
		Resenna r = dao.consultarResenna(resenna.getId());
		if (r != null) {
			dao.eliminarResenna(r);
			return "Confirmaciones.html";
		} else {
			return "Errores.html";
		}
	}
}
