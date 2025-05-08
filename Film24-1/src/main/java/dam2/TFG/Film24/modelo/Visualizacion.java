package dam2.TFG.Film24.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Visualizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Pelicula pelicula;

	private boolean visualizada;

	// Por ejemplo, también podrías guardar la fecha
	private LocalDateTime fechaVisualizacion;

	public Visualizacion() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public boolean isVisualizada() {
		return visualizada;
	}

	public void setVisualizada(boolean visualizada) {
		this.visualizada = visualizada;
	}

	public LocalDateTime getFechaVisualizacion() {
		return fechaVisualizacion;
	}

	public void setFechaVisualizacion(LocalDateTime fechaVisualizacion) {
		this.fechaVisualizacion = fechaVisualizacion;
	}

}
