package dam2.TFG.Film24.modelo;

import java.util.ArrayList;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String titulo;
	private String descripcion;
	private String genero;
	private String director;
	private int anno;
	
	@ManyToMany(mappedBy="peliculas", cascade=CascadeType.ALL)
	private List<Usuario> usuarios;
	
	@OneToMany(mappedBy="pelicula", cascade=CascadeType.ALL)
	private List<Resenna> resennas;
	
	public Pelicula() {
		
	}
	
	public Pelicula(String titulo, String descripcion, String genero, String director, int anno) {
		this.titulo=titulo;
		this.descripcion=descripcion;
		this.genero=genero;
		this.director=director;
		this.anno=anno;
		usuarios=new ArrayList<>();
		resennas=new ArrayList<>();
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
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
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
}
