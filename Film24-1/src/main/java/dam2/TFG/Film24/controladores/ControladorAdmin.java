package dam2.TFG.Film24.controladores;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dam2.TFG.Film24.dao.Film24DAO;
import dam2.TFG.Film24.modelo.LineaPedidoDTO;
import dam2.TFG.Film24.modelo.Noticia;
import dam2.TFG.Film24.modelo.Pedido;
import dam2.TFG.Film24.modelo.Pelicula;
import dam2.TFG.Film24.modelo.Producto;
import dam2.TFG.Film24.modelo.Resenna;
import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.repository.LineaPedidoRepository;
import dam2.TFG.Film24.repository.PeliculaRepository;
import dam2.TFG.Film24.repository.ProductoRepository;
import dam2.TFG.Film24.repository.ResennaRepository;

@Controller
public class ControladorAdmin {

	@Autowired
	private Film24DAO dao;

	@Autowired
	private PeliculaRepository peliculaRepository;

	@Autowired
	private ResennaRepository resennaRepository;

	@Autowired
	private LineaPedidoRepository lineaPedidoRepository;

	
	@GetMapping("/interfazAdmin")
	public String mostrarInterfazAdmin() {
		return "interfazAdmin";
	}

	@GetMapping("/listaPeliculas")
	public String obtenerPeliculas(Model model) {
		List<Pelicula> listaPeliculas = dao.listaPeliculas();
		model.addAttribute("listaPeliculas", listaPeliculas);
		return "listaPeliculas";
	}

	@GetMapping("/listaUsuarios")
	public String obtenerUsuarios(Model model) {
		List<Usuario> listaUsuarios = dao.listaUsuarios();
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "listaUsuarios";
	}
	
	
	@GetMapping("/listaNoticias")
	public String obtenerPosts(Model model) {
		List<Noticia> listaNoticias = dao.listaNoticias();
		model.addAttribute("listaNoticias", listaNoticias);
		return "listaNoticias";
	}


	@GetMapping("/detallePelicula/{id}")
	public String detallePelicula(@PathVariable("id") int id, Model model) {
		Pelicula pelicula = peliculaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Película no encontrada"));
		List<Resenna> resennas = resennaRepository.findByPeliculaId(id);

		model.addAttribute("pelicula", pelicula);
		model.addAttribute("resennas", resennas);
		return "detallePelicula";
	}
	
	
	

	@Autowired
	private ProductoRepository productoRepository;

	@GetMapping("/listaProductosAdmin")
	public String listaProductos(Model model) {
		List<Producto> productos = dao.listaProductos();
		model.addAttribute("productos", productos);

		return "listaProductosAdmin";
	}

	@GetMapping("/eliminarProducto")
	public String mostrarProductos(Model model) {
		List<Producto> productos = productoRepository.findAll();
		model.addAttribute("productos", productos);
		return "eliminarProducto";
	}

	@PostMapping("/eliminarProducto/submit")
	public String eliminarProductoSubmit(@RequestParam("id") int id, @RequestParam("accion") String accion, Model model) {
	    if ("delete".equals(accion)) {
	        if (productoRepository.existsById(id)) {
	            if (lineaPedidoRepository.existsByProductoId(id)) {
	                model.addAttribute("estado", "errorVinculado");
	                return "confirmacionEliminarProducto";
	            }
	            productoRepository.deleteById(id);
	            model.addAttribute("estado", "exito");
	            return "confirmacionEliminarProducto";
	        } else {
	            model.addAttribute("estado", "errorNoEncontrado");
	            return "confirmacionEliminarProducto";
	        }
	    }
	    return "redirect:/listaProductosAdmin";
	}
	
	

	@PostMapping("/productos/aumentarStock")
	public String aumentarStock(@RequestParam int productoId, @RequestParam int cantidad, Model model) {
	    Optional<Producto> optionalProducto = productoRepository.findById(productoId);
	    if (optionalProducto.isPresent()) {
	        Producto producto = optionalProducto.get();
	        producto.setStock(producto.getStock() + cantidad);
	        productoRepository.save(producto);
	        model.addAttribute("mensaje", "Stock actualizado correctamente.");
	    } else {
	        model.addAttribute("error", "Producto no encontrado.");
	    }
	    model.addAttribute("productos", productoRepository.findAll());
	    return "listaProductosAdmin";
	}
	
	@GetMapping("/listaPedidosAdmin")
	public String listaPedidosAdmin(Model model) {
	    List<Pedido> pedidos = dao.listaPedidos();

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	    // Convertir pedidos a DTO para facilitar la vista
	    List<Object> pedidosDTO = pedidos.stream().map(p -> {
	        List<LineaPedidoDTO> lineasDTO = p.getLineas().stream().map(linea -> 
	            new LineaPedidoDTO(
	                linea.getProducto().getNombre(),
	                linea.getCantidad(),
	                linea.getSubtotal(),
	                linea.getProducto().getImagen()
	            )
	        ).collect(Collectors.toList());

	        return new Object() {
	            public final int id = p.getId();
	            public final String fechaFormateada = p.getFecha().format(formatter);
	            public final double total = p.getTotal();
	            public final List<LineaPedidoDTO> lineas = lineasDTO;
	            public final String usuarioNombre = p.getUsuario().getNombre() + " " + p.getUsuario().getApellido();
	        };
	    }).collect(Collectors.toList());

	    model.addAttribute("pedidos", pedidosDTO);

	    return "listaPedidosAdmin"; // Nueva plantilla para administración
	}



}
