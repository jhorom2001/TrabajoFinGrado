package dam2.TFG.Film24.modelo;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	private int edad;
	
	@Column(unique=true)
	private String correoElectronico;
	
	private String password;
	private String rol;
	
	@ManyToMany
	@JoinTable(name = "usuario_peliculas", joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
	private List<Pelicula> peliculas;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<Resenna> resennas;
	
	public Usuario() {
		
	}
	
	public Usuario(String nombre, String apellido, int edad, String correoElectronico, String password) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.edad=edad;
		this.correoElectronico=correoElectronico;
		this.password=password;
		peliculas=new ArrayList<>();
		resennas=new ArrayList<>();
		rol="USUARIO";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	public List<Resenna> getResennas() {
		return resennas;
	}

	public void setResennas(List<Resenna> resennas) {
		this.resennas = resennas;
	}

	public boolean esAdmin() {
        return "ADMIN".equalsIgnoreCase(this.rol);
    }
}
