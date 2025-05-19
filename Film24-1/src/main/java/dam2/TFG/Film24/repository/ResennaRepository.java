package dam2.TFG.Film24.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Resenna;
import dam2.TFG.Film24.modelo.Usuario;

public interface ResennaRepository extends JpaRepository<Resenna, Integer> {
    List<Resenna> findByPeliculaId(int peliculaId);
    Optional<Resenna> findByUsuarioAndPelicula(Usuario usuario, Pelicula pelicula);
}
