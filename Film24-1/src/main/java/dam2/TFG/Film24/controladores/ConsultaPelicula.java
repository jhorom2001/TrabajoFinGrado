package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;

@Controller
public class ConsultaPelicula {
	
//	@Autowired
//	private Film24DAO dao;
//	
//	@GetMapping("/pelicula/consulta")
//	public String consultaPelicula(Model model) {
//		model.addAttribute("peliculaConsultaForm", new Pelicula());
//		return "ConsultaPelicula.html";
//	}
//	
//	@PostMapping("/pelicula/consulta/submit")
//	public String consultaPeliculaSubmit(Pelicula pelicula, Model model) {
//		return "redirect:/pelicula/consulta/" + pelicula.getId();
//	}
//	
//	@GetMapping("/pelicula/consulta/{id}")
//	public String consultaPeliculaResultado(@PathVariable("id") int id, Model model) {
//		model.addAttribute("id", id);
//		Pelicula pelicula=dao.consultarPelicula(id);
//		model.addAttribute("peliculaConsultaForm", pelicula==null? new Pelicula(): pelicula);
//		return "ConsultaPelicula.html";
//	}

}
