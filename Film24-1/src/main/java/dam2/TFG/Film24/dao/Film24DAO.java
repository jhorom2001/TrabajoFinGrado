package dam2.TFG.Film24.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Producto;
import dam2.TFG.Film24.modelo.Resenna;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.modelo.Visualizacion;
import dam2.TFG.Film24.repository.UsuarioRepository;
import dam2.TFG.Film24.repository.VisualizacionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class Film24DAO {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private VisualizacionRepository visualizacionRepository;

	@PersistenceContext
	private EntityManager em;

	// USUARIOS
	public void altaUsuario(Usuario u) {
		em.persist(u);
	}

	public void eliminarUsuario(Usuario u) {
		em.remove(u);
	}

	public void modificarUsuario(Usuario u) {
		Usuario usuario = em.find(Usuario.class, u.getId());
		if (usuario != null) {
			usuario.setNombre(u.getNombre());
			usuario.setApellido(u.getApellido());
			usuario.setEdad(u.getEdad());
		} else {
			System.out.println("Usuario no encontrado con el ID proporcionado.");
		}
	}

	public Usuario consultaUsuario(int id) {
		return em.find(Usuario.class, id);
	}

	// PELICULAS
	public void altaPelicula(Pelicula p) {
		em.persist(p);
	}

	public void eliminarPelicula(Pelicula p) {
		em.remove(p);
	}

	public void modificarPelicula(Pelicula p) {
		Pelicula pelicula = em.find(Pelicula.class, p.getId());
		if (pelicula != null) {
			pelicula.setDescripcion(p.getDescripcion());
			pelicula.setCategoria(p.getCategoria());
			pelicula.setDuracion(p.getDuracion());
		} else {
			System.out.println("Película no encontrada con el ID proporcionado.");
		}
	}

	public Pelicula consultarPelicula(int id) {
		return em.find(Pelicula.class, id);
	}

	// RESENNAS
	public void altaResenna(Resenna r) {
		em.persist(r);
	}

	public void eliminarResenna(Resenna r) {
		em.remove(r);
	}

	public void modificarResenna(Resenna r) {
		Resenna resenna = em.find(Resenna.class, r.getId());
		if (resenna != null) {
			resenna.setComentario(r.getComentario());
			resenna.setPuntuacion(r.getPuntuacion());
		}
	}

	public Resenna consultarResenna(int id) {
		return em.find(Resenna.class, id);
	}

	// ASIGNAR PELICULA
	public void asignarPelicula(Usuario u, Pelicula p) {
		u = usuarioRepository.findById(u.getId()).orElse(null);

		if (u != null && p != null) {
			// Registrar visualización
			Visualizacion visualizacion = new Visualizacion();
			visualizacion.setUsuario(u);
			visualizacion.setPelicula(p);
			visualizacion.setVisualizada(true);

			visualizacionRepository.save(visualizacion);
		} else {
			throw new IllegalArgumentException("Usuario o película no encontrada");
		}
	}

	// DEVOLVER PELICULA
	public void finalizarVisualizacion(Usuario usuario, Pelicula pelicula) {
	    Visualizacion visualizacion = visualizacionRepository.findTopByUsuarioAndPeliculaOrderByFechaVisualizacionDesc(usuario, pelicula);

	    if (visualizacion == null) {
	        throw new IllegalArgumentException("No se encontró la visualización más reciente para el usuario y la película.");
	    }

	    visualizacion.setVisualizada(false); // o lo que corresponda para marcarla como finalizada
	    visualizacionRepository.save(visualizacion);
	}

	public void actualizarVisualizacion(Visualizacion visualizacion) {
		visualizacion.setVisualizada(true); // Actualizar el estado a "finalizado"
		visualizacionRepository.save(visualizacion);
	}

	// PRODUCTOS
	public void altaProducto(Producto producto) {
		em.persist(producto);
	}

	// consultas de productos

	public Producto consultarProducto(int id) {
		return em.find(Producto.class, id);
	}

	public List<Producto> listaProductos() {
		String jpql = "SELECT p FROM Producto p";
		return em.createQuery(jpql, Producto.class).getResultList();
	}
	
	

	// CONSULTAS
	public List<Pelicula> listaPeliculas() {
		String jpql = "SELECT p FROM Pelicula p";
		return em.createQuery(jpql, Pelicula.class).getResultList();
	}

	public List<Usuario> listaUsuarios() {
		String jpql = "SELECT u FROM Usuario u";
		return em.createQuery(jpql, Usuario.class).getResultList();
	}

	// AÑADIDO
	public List<Visualizacion> obtenerVisualizacionesPorUsuario(Usuario usuario) {
		return visualizacionRepository.findByUsuario(usuario);
	}
}
