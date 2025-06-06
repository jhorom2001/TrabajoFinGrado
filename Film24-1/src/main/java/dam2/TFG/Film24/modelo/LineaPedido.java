package dam2.TFG.Film24.modelo;

import jakarta.persistence.*;

@Entity
public class LineaPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int cantidad;
	private double precioUnitario;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	public LineaPedido() {
	}

	public LineaPedido(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnitario = producto.getPrecio();
	}

	public LineaPedido(Producto producto, int cantidad, double precio) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnitario = precio;
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	// Método de utilidad para calcular el subtotal de esta línea
	public double getSubtotal() {
		return cantidad * precioUnitario;
	}
}
