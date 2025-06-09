package dam2.TFG.Film24.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Noticia;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Resenna;
import dam2.TFG.Film24.repository.NoticiaRepository;

@Controller
public class ControladorNoticias {

	@Autowired
	private Film24DAO dao;

	@Autowired
	NoticiaRepository noticiaRepository;

	// ALTA
	@GetMapping("/altaNoticia")
	public String altaNoticia(Model model) {
		model.addAttribute("noticiaForm", new Noticia());
		return "altaNoticia";
	}

	@PostMapping("/altaNoticia/submit")
	public String altaNoticiaSubmit(Noticia noticia, Model model) {
		dao.altaNoticia(noticia);
		model.addAttribute("noticiaForm", noticia);
		return "confirmacionNoticia";
	}

	// ELIMINAR
	@GetMapping("/eliminarNoticia")
	public String EliminarobtenerNoticias(Model model) {
		List<Noticia> listaNoticias = dao.listaNoticias();
		model.addAttribute("listaNoticias", listaNoticias);
		return "eliminarNoticia";
	}

	@PostMapping("/eliminarNoticia/submit")
	public String EliminarNoticiaSubmit(Noticia noticia, Model model) {
		Noticia n = dao.consultarNoticia(noticia.getId());
		if (n != null) {
			dao.eliminarNoticia(n);
			return "confirmacionEliminarNoticia.html";
		} else {
			return "Errores.html";
		}
	}

	@GetMapping("/postsParaUsuario")
	public String mostrarPosts(Model model) {
		List<Noticia> listaNoticias = dao.listaNoticias(); // Obtener todas las noticias
		model.addAttribute("listaNoticias", listaNoticias);
		return "postsParaUsuario";
	}

	

	@GetMapping("/usuarioLogeado")
	public String usuarioLogeado(Model model) {
		List<Noticia> listaNoticias = dao.listaNoticias();
		model.addAttribute("listaNoticias", listaNoticias);
		return "usuarioLogeado";
	}
	
	@GetMapping("/detalleNoticia/{id}")
	public String detalleNoticia(@PathVariable("id") int id, Model model) {
		Noticia noticia = noticiaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Noticia no encontrada"));
		model.addAttribute("noticia", noticia);
		return "detalleNoticia";
	}
	
	
	
	
	

}
