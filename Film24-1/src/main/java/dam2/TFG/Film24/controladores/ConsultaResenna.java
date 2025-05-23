package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Resenna;

@Controller
public class ConsultaResenna {
	
//	@Autowired
//	private Film24DAO dao;
//	
//	@GetMapping("/resenna/consulta")
//	public String consultaResenna(Model model) {
//		model.addAttribute("resennaConsultaForm", new Resenna());
//		return "ConsultaResenna.html";
//	}
//	
//	@PostMapping("/resenna/consulta/submit")
//	public String consultaResennaSubmit(Resenna resenna, Model model) {
//		return "redirect:/resenna/consulta/" + resenna.getId();
//	}
//	
//	@GetMapping("/resenna/consulta/{id}")
//	public String consultaResennaResultado(@PathVariable("id") int id, Model model) {
//		model.addAttribute("id", id);
//		Resenna resenna=dao.consultarResenna(id);
//		model.addAttribute("resennaConsultaForm", resenna==null? new Resenna(): resenna);
//		return "ConsultaUsuario.html";
//	}

}
