package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Service.CarritoService;
import com.puntodecorte.oposiciones.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarritoController {

    private final CarritoService carritoService;
    private final UsuarioService usuarioService;

    public CarritoController(CarritoService carritoService,
                             UsuarioService usuarioService) {

        this.carritoService = carritoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model) {

        Long idUsu = usuarioService
                .obtenerUsuarioActual()
                .getId();

        model.addAttribute(
                "items",
                carritoService.obtenerCarrito(idUsu)
        );

        model.addAttribute(
                "total",
                carritoService.calcularTotal(idUsu)
        );

        return "carrito";
    }

    @PostMapping("/carrito/anadir/{idProduc}")
    public String añadir(@PathVariable Long idProduc,
                         @RequestHeader(value = "referer",
                                 required = false) String referer) {

        Long idUsu = usuarioService
                .obtenerUsuarioActual()
                .getId();

        carritoService.añadir(idUsu, idProduc);

        if (referer != null) {
            return "redirect:" + referer;
        }

        return "redirect:/tienda";
    }

    @PostMapping("/carrito/eliminar/{idCarrito}")
    public String eliminar(@PathVariable Long idCarrito) {

        carritoService.eliminar(idCarrito);

        return "redirect:/carrito";
    }

    // NUEVO

    @PostMapping("/carrito/enviar")
    public String enviarPedido(RedirectAttributes redirectAttributes) {

        Long idUsu = usuarioService
                .obtenerUsuarioActual()
                .getId();

        carritoService.vaciarCarrito(idUsu);

        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Pedido enviado correctamente a la academia"
        );

        return "redirect:/carrito";
    }
}