package dam2.TFG.Film24.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;

@Controller
public class EliminarPelicula {

	@Autowired
	private Film24DAO dao;
	
	@GetMapping("/eliminarPelicula")
	public String EliminarobtenerPeliculas(Model model) {
        List<Pelicula> listaPeliculas = dao.listaPeliculas();
        model.addAttribute("listaPeliculas", listaPeliculas);
        return "eliminarPelicula";
    }
	
	@PostMapping("/eliminarPelicula/submit")
	public String EliminarPeliculaSubmit(Pelicula pelicula, Model model) {
		Pelicula p=dao.consultarPelicula(pelicula.getId());
		if(p!=null) {
			dao.eliminarPelicula(p);
			return "confirmacionEliminarPelicula.html";
		}
		else {
			return "Errores.html";
		}
	}
}
