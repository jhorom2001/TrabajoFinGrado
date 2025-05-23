package dam2.TFG.Film24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dam2.TFG.Film24.modelo.LineaPedido;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Integer> {
    boolean existsByProductoId(int productoId);
}
