package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Producto;
import com.puntodecorte.oposiciones.Repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    private static final String UPLOAD_DIR =
            System.getProperty("user.home") + "/puntodecorte/uploads/productos/";

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listarTodos() {
        return repo.findAll();
    }

    public List<Producto> listarPorCategoria(String categoria) {
        return repo.findByCategoria(categoria);
    }

    public Optional<Producto> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public void guardar(Producto producto, MultipartFile imagen) throws IOException {
        if (imagen != null && !imagen.isEmpty()) {
            producto.setUrlProduc(guardarImagen(imagen));
        }
        repo.save(producto);
    }

    public void actualizar(Long id, Producto datos, MultipartFile imagen) throws IOException {
        Producto existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setNombreProduc(datos.getNombreProduc());
        existente.setDescripProduc(datos.getDescripProduc());
        existente.setPrecio(datos.getPrecio());
        existente.setCategoria(datos.getCategoria());

        if (imagen != null && !imagen.isEmpty()) {
            existente.setUrlProduc(guardarImagen(imagen));
        }

        repo.save(existente);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    private String guardarImagen(MultipartFile imagen) throws IOException {
        String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
        Path ruta = Paths.get(UPLOAD_DIR + nombreArchivo);
        Files.createDirectories(ruta.getParent());
        Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
        return "/uploads/productos/" + nombreArchivo;
    }
}