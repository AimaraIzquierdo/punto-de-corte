package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Service.UsuarioService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControladorGlobalUsuario {

    private final UsuarioService usuarioService;

    public ControladorGlobalUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ModelAttribute
    public void addUsuario(Model model) {

        try {
            model.addAttribute(
                    "usuario",
                    usuarioService.obtenerUsuarioActual()
            );
        } catch (Exception e) {
            // Usuario no autenticado (login, registro, etc.)
        }
    }
}