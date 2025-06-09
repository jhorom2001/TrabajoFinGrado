package dam2.TFG.Film24.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dam2.TFG.Film24.modelo.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Integer>{
	Optional<Noticia> findById(Integer id);


}
