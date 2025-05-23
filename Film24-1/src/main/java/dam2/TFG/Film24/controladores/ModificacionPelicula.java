package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;

@Controller
public class ModificacionPelicula {
	
//	@Autowired
//	private Film24DAO dao;
//	
//	@GetMapping("pelicula/modificacion")
//	public String peliculaUsuario(Model model) {
//		model.addAttribute("peliculaForm", new Pelicula());
//		return "ModificacionPelicula.html";
//	}
//	
//	@PostMapping("pelicula/modificacion/submit")
//	public String modificarPeliculaSubmit(Pelicula pelicula, Model model) {
//		if(dao.consultarPelicula(pelicula.getId())!=null) {
//			dao.modificarPelicula(pelicula);
//			return "Confirmaciones.html";
//		}
//		else {
//			return "Errores.html";
//		}
//		
//	}

}
