package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.CarritoItem;
import com.puntodecorte.oposiciones.Dominio.Producto;
import com.puntodecorte.oposiciones.Repository.CarritoRepository;
import com.puntodecorte.oposiciones.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepo;
    private final ProductoRepository productoRepo;

    public CarritoService(CarritoRepository carritoRepo, ProductoRepository productoRepo) {
        this.carritoRepo = carritoRepo;
        this.productoRepo = productoRepo;
    }

    public List<CarritoItem> obtenerCarrito(Long idUsu) {
        return carritoRepo.findByIdUsu(idUsu);
    }

    public void añadir(Long idUsu, Long idProduc) {
        Optional<CarritoItem> existente =
                carritoRepo.findByIdUsuAndProductoIdProduc(idUsu, idProduc);

        if (existente.isPresent()) {
            // Si ya está en el carrito, suma uno
            CarritoItem item = existente.get();
            item.setCantidad(item.getCantidad() + 1);
            carritoRepo.save(item);
        } else {
            Producto producto = productoRepo.findById(idProduc)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            CarritoItem item = new CarritoItem();
            item.setIdUsu(idUsu);
            item.setProducto(producto);
            item.setCantidad(1);
            carritoRepo.save(item);
        }
    }

    public void eliminar(Long idCarrito) {
        carritoRepo.deleteById(idCarrito);
    }

    public Float calcularTotal(Long idUsu) {
        return obtenerCarrito(idUsu).stream()
                .map(i -> i.getProducto().getPrecio() * i.getCantidad())
                .reduce(0f, Float::sum);
    }
}