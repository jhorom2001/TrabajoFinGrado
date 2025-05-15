package dam2.TFG.Film24.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Noticia;
import dam2.TFG.Film24.modelo.Pelicula;

@Controller
public class ControladorNoticias {
	
	@Autowired
    private Film24DAO dao;
	
	//ALTA
    @GetMapping("/altaNoticia")
    public String altaNoticia(Model model) {
        model.addAttribute("noticiaForm", new Noticia());
        return "altaNoticia";
    }

    @PostMapping("/altaNoticia/submit")
    public String altaNoticiaSubmit(Noticia noticia, Model model) {
        dao.altaNoticia(noticia);
        model.addAttribute("noticiaForm", noticia);
        return "Confirmaciones";
    }
    
    //ELIMINAR
    @GetMapping("/eliminarNoticia")
	public String EliminarobtenerNoticias(Model model) {
        List<Noticia> listaNoticias = dao.listaNoticias();
        model.addAttribute("listaNoticias", listaNoticias);
        return "eliminarNoticia";
    }
    
    @PostMapping("/eliminarNoticia/submit")
	public String EliminarNoticiaSubmit(Noticia noticia, Model model) {
		Noticia n=dao.consultarNoticia(noticia.getId());
		if(n!=null) {
			dao.eliminarNoticia(n);
			return "Confirmaciones.html";
		}
		else {
			return "Errores.html";
		}
	}
    
    @GetMapping("/postsParaUsuario")
    public String mostrarPosts(Model model) {
        List<Noticia> listaNoticias = dao.listaNoticias();  // Obtener todas las noticias
        model.addAttribute("listaNoticias", listaNoticias); // Añadir al modelo para Thymeleaf
        return "postsParaUsuario"; // Devuelve el HTML para mostrar la tabla
    }

}
