package dam2.TFG.Film24.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import dam2.TFG.Film24.modelo.Resenna;

public interface ResennaRepository extends JpaRepository<Resenna, Integer> {
    List<Resenna> findByPeliculaId(int peliculaId);
}
