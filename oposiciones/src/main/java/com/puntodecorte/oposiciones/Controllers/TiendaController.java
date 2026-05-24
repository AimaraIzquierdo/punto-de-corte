package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Producto;
import com.puntodecorte.oposiciones.Service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class TiendaController {

    private final ProductoService service;

    public TiendaController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/tienda")
    public String adminTienda(@RequestParam(required = false) String categoria, Model model) {
        List<Producto> productos = (categoria != null && !categoria.isBlank())
                ? service.listarPorCategoria(categoria)
                : service.listarTodos();

        model.addAttribute("productos", productos);
        model.addAttribute("categoriaActiva", categoria);
        return "tienda_admin";
    }

    @GetMapping("/tienda/admin/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", List.of("suplementos", "ropa", "accesorios", "material"));
        return "tienda_form";
    }

    @PostMapping("/tienda/admin/nuevo")
    public String crearProducto(@ModelAttribute Producto producto,
                                @RequestParam("imagen") MultipartFile imagen) throws IOException {
        service.guardar(producto, imagen);
        return "redirect:/tienda";
    }

    @GetMapping("/tienda/admin/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Producto producto = service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", List.of("suplementos", "ropa", "accesorios", "material"));
        return "tienda_form";
    }

    @PostMapping("/tienda/admin/editar/{id}")
    public String editarProducto(@PathVariable Long id,
                                 @ModelAttribute Producto producto,
                                 @RequestParam("imagen") MultipartFile imagen) throws IOException {
        service.actualizar(id, producto, imagen);
        return "redirect:/tienda";
    }

    @PostMapping("/tienda/admin/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/tienda";
    }
}