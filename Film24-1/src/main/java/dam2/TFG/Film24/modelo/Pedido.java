package dam2.TFG.Film24.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime fecha;

    private double total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<LineaPedido> lineas = new ArrayList<>();

    public Pedido() {
        this.fecha = LocalDateTime.now();
    }

    public Pedido(Usuario usuario) {
        this.usuario = usuario;
        this.fecha = LocalDateTime.now();
        this.lineas = new ArrayList<>();
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
        linea.setPedido(this);
        recalcularTotal();
    }

    public void recalcularTotal() {
        total = lineas.stream()
                      .mapToDouble(LineaPedido::getSubtotal)
                      .sum();
    }
}

