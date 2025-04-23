package dam2.TFG.Film24.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Usuario;

@Controller
public class DevolverPelicula {
	
	@Autowired
	private Film24DAO dao;
	
	//DEVOLVER EJEMPLAR
	@GetMapping("pelicula/devolverPelicula")
	public String devolverPelicula(Model model) {
		model.addAttribute("devolverForm", new Pelicula());
		return "DevolverPelicula.html";
	}
	
	@PostMapping("/pelicula/devolverPelicula/submit")
    public String devolverPelicula(Pelicula pelicula, Model model) {
        Usuario u = dao.consultaUsuario(pelicula.getUsuarios().get(0).getId());
        Pelicula p = dao.consultarPelicula(pelicula.getId());

        if (u != null && p != null) {
            dao.devolverPelicula(u, p); // Eliminar la relación
            return "Confirmaciones.html";
        } else {
            return "Errores.html";
        }
    }
	
}


