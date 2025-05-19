package dam2.TFG.Film24.controladores;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.modelo.Visualizacion;
import dam2.TFG.Film24.security.MyUserDetails;

@Controller
public class AltaPelicula {

    @Autowired
    private Film24DAO dao;

    @GetMapping("/altaPelicula")
    public String altaPelicula(Model model) {
        model.addAttribute("peliculaForm", new Pelicula());
        return "altaPelicula";
    }

    @PostMapping("/altaPelicula/submit")
    public String altaPeliculaSubmit(Pelicula pelicula, Model model) {
        dao.altaPelicula(pelicula);
        model.addAttribute("peliculaForm", pelicula);
        return "Confirmaciones";
    }

    //AÑADIDO
    @GetMapping("/listaPeliculasParaUsuario")
    public String obtenerPeliculas(Model model, Authentication authentication) {
        List<Pelicula> listaPeliculas = dao.listaPeliculas();
        model.addAttribute("listaPeliculas", listaPeliculas);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Usuario usuario = userDetails.getUsuario();

        List<Visualizacion> visualizaciones = dao.obtenerVisualizacionesPorUsuario(usuario);
        List<Integer> peliculasVisualizadas = visualizaciones.stream().filter(Visualizacion::isEnProgreso)
            .map(v -> v.getPelicula().getId())
            .toList();

        model.addAttribute("peliculasVisualizadas", peliculasVisualizadas);
        return "listaPeliculasParaUsuario";
    }
}
