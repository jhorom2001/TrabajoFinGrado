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

    @GetMapping("/altaPelicula")// este es del html de interfazAdmin
    public String altaPelicula(Model model) {// pasar datos desde el contralador a la vista(html)
        model.addAttribute("peliculaForm", new Pelicula());
        return "altaPelicula"; //este es el propio html(formulario vacio)
    }

    @PostMapping("/altaPelicula/submit") // formulario relleno del html de confirmaciones
    public String altaPeliculaSubmit(Pelicula pelicula, Model model) { // objeto que se rellena cogiendo los campos
        dao.altaPelicula(pelicula);
        model.addAttribute("peliculaForm", pelicula);
        return "confirmacionAltaPelicula"; // html de dado de alta correctamente
    }

    @GetMapping("/listaPeliculasParaUsuario")
    public String obtenerPeliculas(Model model, Authentication authentication) {
        List<Pelicula> listaPeliculas = dao.listaPeliculas();
        model.addAttribute("listaPeliculas", listaPeliculas);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Usuario usuario = userDetails.getUsuario();

        List<Visualizacion> visualizaciones = dao.obtenerVisualizacionesPorUsuario(usuario);
        List<Integer> peliculasVisualizadas = visualizaciones.stream().filter(Visualizacion::isEnProgreso)
            .map(v -> v.getPelicula().getId()).toList();// obtener el objeto de la pelicula y su id
            

        model.addAttribute("peliculasVisualizadas", peliculasVisualizadas);
        return "listaPeliculasParaUsuario";
    }
}
