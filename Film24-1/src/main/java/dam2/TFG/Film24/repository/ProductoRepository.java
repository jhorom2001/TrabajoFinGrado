package dam2.TFG.Film24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dam2.TFG.Film24.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	
}
