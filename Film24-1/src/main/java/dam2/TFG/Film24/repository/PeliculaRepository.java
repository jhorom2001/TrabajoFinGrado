package dam2.TFG.Film24.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dam2.TFG.Film24.modelo.Pelicula;


public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    List<Pelicula> findByCategoria(String categoria);
    Optional<Pelicula> findById(Integer id);
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
    @Query("SELECT p FROM Pelicula p JOIN p.resennas r GROUP BY p ORDER BY AVG(r.puntuacion) DESC")
    List<Pelicula> findPeliculasOrderByMediaPuntuacion();
    
}