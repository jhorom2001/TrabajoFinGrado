package dam2.TFG.Film24.modelo;

//Clase necesaria para transformar el formato de fecha y hora



import java.util.List;

public class PedidoDTO {
    private int id;
    private String fechaFormateada;
    private double total;
    private List<LineaPedidoDTO> lineas;

    public PedidoDTO(int id, String fechaFormateada, double total, List<LineaPedidoDTO> lineas) {
        this.id = id;
        this.fechaFormateada = fechaFormateada;
        this.total = total;
        this.lineas = lineas;
    }

    public int getId() {
        return id;
    }

    public String getFechaFormateada() {
        return fechaFormateada;
    }

    public double getTotal() {
        return total;
    }

    public List<LineaPedidoDTO> getLineas() {
        return lineas;
    }
}
