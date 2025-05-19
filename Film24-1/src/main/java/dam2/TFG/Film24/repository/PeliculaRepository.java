package dam2.TFG.Film24.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dam2.TFG.Film24.modelo.Pelicula;


public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    List<Pelicula> findByCategoria(String categoria);
    Optional<Pelicula> findById(Integer id);
    
}