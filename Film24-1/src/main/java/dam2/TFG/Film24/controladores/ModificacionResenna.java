package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Resenna;

@Controller
public class ModificacionResenna {

//	@Autowired
//	private Film24DAO dao;
//	
//	@GetMapping("resenna/modificacion")
//	public String modificarResenna(Model model) {
//		model.addAttribute("resennaForm", new Resenna());
//		return "ModificacionResenna.html";
//	}
//	
//	@PostMapping("resenna/modificacion/submit")
//	public String modificarResennaSubmit(Resenna resenna, Model model) {
//		if(dao.consultarResenna(resenna.getId())!=null) {
//			dao.modificarResenna(resenna);
//			return "Confirmaciones.html";
//		}
//		else {
//			return "Errores.html";
//		}
//		
//	}
}
