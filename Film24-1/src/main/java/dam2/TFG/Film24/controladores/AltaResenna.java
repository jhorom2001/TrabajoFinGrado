package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Resenna;

@Controller
public class AltaResenna {

	@Autowired
	private Film24DAO dao;
	
	@GetMapping("/resenna/alta")
	public String altaResenna(Model model) {
		model.addAttribute("resennaForm", new Resenna());
		return "altaResenna.html";
	}
	
	@PostMapping("/resenna/alta/submit")
	public String altaResennaSubmit(Resenna resenna, Model model) {
		model.addAttribute("resennaForm", resenna);
		dao.altaResenna(resenna);
		return "Confirmaciones.html";
	}
}
