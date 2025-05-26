package dam2.TFG.Film24.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dam2.TFG.Film24.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	 Optional<Usuario> findByCorreoElectronico(String correoElectronico);
	 boolean existsByCorreoElectronico(String correoElectronico);

}
