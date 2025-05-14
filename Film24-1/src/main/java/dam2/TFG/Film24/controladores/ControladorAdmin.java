package dam2.TFG.Film24.controladores;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Resenna;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.repository.PeliculaRepository;
import dam2.TFG.Film24.repository.ResennaRepository;

@Controller
public class ControladorAdmin {
	
	@Autowired
	private Film24DAO dao;
	
	@Autowired
    private PeliculaRepository peliculaRepository;
	
	@Autowired
	private ResennaRepository resennaRepository;
	
	@GetMapping("/loginadmin")
	public String mostrarLoginAdmin() {
	    return "loginadmin";
	}
	
	@GetMapping("/interfazAdmin")
	public String mostrarInterfazAdmin() {
	    return "interfazAdmin";
	}
	
	@GetMapping("/listaPeliculas")
    public String obtenerPeliculas(Model model) {
        List<Pelicula> listaPeliculas = dao.listaPeliculas();
        model.addAttribute("listaPeliculas", listaPeliculas);
        return "listaPeliculas";
    }
	
	@GetMapping("/listaUsuarios")
    public String obtenerUsuarios(Model model) {
        List<Usuario> listaUsuarios=dao.listaUsuarios();
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "listaUsuarios";
    }
	
	@GetMapping("/detallePelicula/{id}")
	public String detallePelicula(@PathVariable("id") int id, Model model) {
	    Pelicula pelicula = peliculaRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Película no encontrada"));
	    List<Resenna> resennas = resennaRepository.findByPeliculaId(id);

	    model.addAttribute("pelicula", pelicula);
	    model.addAttribute("reseñas", resennas);  
	    return "detallePelicula";
	}
}
