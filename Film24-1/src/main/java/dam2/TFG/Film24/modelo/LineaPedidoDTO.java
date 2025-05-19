package dam2.TFG.Film24.modelo;




public class LineaPedidoDTO {

    private String nombreProducto;
    private int cantidad;
    private double subtotal;
    private String imagenProducto;

    public LineaPedidoDTO(String nombreProducto, int cantidad, double subtotal, String imagenProducto) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.imagenProducto = imagenProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }
}
