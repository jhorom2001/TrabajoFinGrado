package dam2.TFG.Film24.dao;

import org.springframework.stereotype.Repository;

import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Resenna;
import dam2.TFG.Film24.modelo.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class Film24DAO {
	
	@PersistenceContext
	private EntityManager em;
	
	//USUARIOS
	public void altaUsuario(Usuario u) {
		em.persist(u);
	}
	
	public void eliminarUsuario(Usuario u) {
		em.remove(u);
	}
	
	public void modificarUsuario(Usuario u) {
		System.out.println("MODIFICAR*** " + u.getId());
		Usuario usuario=em.find(Usuario.class, u.getId());
		if(usuario!=null) {
			usuario.setNombre(u.getNombre());
			usuario.setApellido(u.getApellido());
			usuario.setEdad(u.getEdad());
		}
		else {
			System.out.println("Usuario no encontrado con el ID proporcionado.");
		}
		
	}
	
	public Usuario consultaUsuario(int id) {
		return (Usuario)em.find(Usuario.class, id);
	}
	
	
	//PELICULAS
	public void altaPelicula(Pelicula p) {
		em.persist(em);
	}
	
	public void eliminarPelicula(Pelicula p) {
		em.remove(p);
	}
	
	public void modificarPelicula(Pelicula p) {
		System.out.println("MODIFICAR*** " + p.getId());
		Pelicula pelicula=em.find(Pelicula.class, p.getId());
		if(pelicula!=null) {
			pelicula.setDescripcion(p.getDescripcion());
			pelicula.setCategoria(p.getCategoria());
			pelicula.setDuracion(p.getDuracion());
		}
		else {
			System.out.println("Usuario no encontrado con el ID proporcionado.");
		}
	}
	
	public Pelicula consultarPelicula(int id) {
		return (Pelicula)em.find(Pelicula.class, id);
	}
	
	
	//RESENNAS
	public void altaResenna(Resenna r) {
		em.persist(r);
	}
	
	public void eliminarResenna(Resenna r) {
		em.remove(r);
	}
	
	public void modificarResenna(Resenna r) {
		Resenna resenna=em.find(Resenna.class, r.getId());
		if(resenna!=null) {
			resenna.setComentario(r.getComentario());
			resenna.setPuntuacion(r.getPuntuacion());
		}
	}
	
	public Resenna consultarResenna(int id) {
		return (Resenna)em.find(Resenna.class,id);
	}
}
