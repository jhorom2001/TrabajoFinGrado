package dam2.TFG.Film24.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.modelo.Visualizacion;

public interface VisualizacionRepository extends JpaRepository<Visualizacion, Integer> {
    Optional<Visualizacion> findByUsuarioAndPelicula(Usuario usuario, Pelicula pelicula);
	List<Visualizacion> findByUsuario(Usuario usuario);
	Visualizacion findTopByUsuarioAndPeliculaOrderByFechaVisualizacionDesc(Usuario usuario, Pelicula pelicula);
	List<Visualizacion> findByUsuarioAndEnProgresoTrue(Usuario usuario);

}
