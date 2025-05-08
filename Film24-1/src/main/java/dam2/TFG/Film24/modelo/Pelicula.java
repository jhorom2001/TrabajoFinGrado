package dam2.TFG.Film24.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String titulo;
	private String descripcion;
	private String categoria;
	private String director;
	private int duracion;
	private int anyo;
	private String imagen;
	private boolean visualizada = false;

	@ManyToMany(mappedBy = "peliculas", cascade = CascadeType.ALL)
	private List<Usuario> usuarios;

	@OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
	private List<Resenna> resennas;

	// añadido
	@OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
	private List<Visualizacion> visualizaciones = new ArrayList<>();

	public Pelicula() {

	}

	public Pelicula(String titulo, String descripcion, String categoria, String director, int anyo, int duracion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.director = director;
		this.anyo = anyo;
		this.duracion = duracion;
		usuarios = new ArrayList<>();
		resennas = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public boolean isVisualizada() {
		return visualizada;
	}

	public void setVisualizada(boolean visualizada) {
		this.visualizada = visualizada;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Resenna> getResennas() {
		return resennas;
	}

	public void setResennas(List<Resenna> resennas) {
		this.resennas = resennas;
	}

	public void annadirUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public void eliminarUsuario(Usuario usuario) {
		Iterator<Usuario> it = usuarios.iterator();

		while (it.hasNext()) {
			Usuario u = it.next();
			if (u.getId() == usuario.getId()) {
				it.remove();
			}
		}
	}
}
