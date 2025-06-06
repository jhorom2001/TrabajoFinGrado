package dam2.TFG.Film24.modelo;

import java.util.ArrayList;
import java.util.Iterator;
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
import jakarta.persistence.Transient;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String dni;
	private String nombre;
	private String apellido;
	private int edad;
	private String direccion;
	private String telefono;
	
	@Column(unique=true)
	private String correoElectronico;
	
	private String password;
	private String rol="USUARIO";
	
	//añadido 
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Visualizacion> visualizaciones = new ArrayList<>();
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<Resenna> resennas;
	
	//Añado lista de pedidos
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Usuario() {
	//Para crear instancias por reflexión desde la base de datos	
	}
	
	public Usuario(String nombre,String dni, String apellido, int edad, String correoElectronico, String password) {
		this.nombre=nombre;
		this.dni=dni;
		this.apellido=apellido;
		this.edad=edad;
		this.correoElectronico=correoElectronico;
		this.password=password;
		resennas=new ArrayList<>();
		rol="USUARIO";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	
	public List<Pedido> getPedidos() {
	    return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
	    this.pedidos = pedidos;
	}
}
