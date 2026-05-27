package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Rol;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Repository.UsuarioRepository;
import com.puntodecorte.oposiciones.Service.PerfilService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    private final PerfilService perfilService;
    private final UsuarioRepository usuarioRepository;

    public PerfilController(PerfilService perfilService, UsuarioRepository usuarioRepository) {
        this.perfilService = perfilService;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Muestra la página de perfil del usuario
     */
    @GetMapping
    public String mostrarPerfil(Authentication authentication, Model model) {
        String email = authentication.getName();
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);

        if (optUsuario.isEmpty()) {
            return "redirect:/login";
        }

        Usuario usuario = optUsuario.get();
        model.addAttribute("usuario", usuario);
        model.addAttribute("isPremium", usuario.getRol() == Rol.OPOPREMIUM);

        return "perfil";
    }

    /**
     * Actualiza datos básicos del usuario
     */
    @PostMapping("/perfil/actualizar")
    public String actualizarDatos(
            Authentication authentication,
            @RequestParam String nombre,
            @RequestParam String apellidos,
            @RequestParam(required = false) String telefono,
            Model model) {

        String email = authentication.getName();
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);

        if (optUsuario.isEmpty()) {
            return "redirect:/login";
        }

        Usuario usuario = optUsuario.get();
        Optional<String> error = perfilService.actualizarDatos(usuario.getId(), nombre, apellidos, telefono);

        if (error.isPresent()) {
            model.addAttribute("error", error.get());
        } else {
            model.addAttribute("success", "Datos actualizados correctamente");
        }

        // Recargar usuario actualizado
        optUsuario = usuarioRepository.findByEmail(email);
        if (optUsuario.isPresent()) {
            model.addAttribute("usuario", optUsuario.get());
            model.addAttribute("isPremium", optUsuario.get().getRol() == Rol.OPOPREMIUM);
        }

        return "perfil";
    }

    /**
     * Cambia la contraseña del usuario
     */
    @PostMapping("/perfil/cambiar-contrasena")
    public String cambiarContrasena(
            Authentication authentication,
            @RequestParam String passwordActual,
            @RequestParam String passwordNueva,
            @RequestParam String passwordConfirm,
            Model model) {

        String email = authentication.getName();
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);

        if (optUsuario.isEmpty()) {
            return "redirect:/login";
        }

        Usuario usuario = optUsuario.get();
        Optional<String> error = perfilService.cambiarContrasena(usuario.getId(), passwordActual, passwordNueva, passwordConfirm);

        if (error.isPresent()) {
            model.addAttribute("errorPassword", error.get());
        } else {
            model.addAttribute("successPassword", "Contraseña cambiada correctamente");
        }

        // Recargar usuario
        optUsuario = usuarioRepository.findByEmail(email);
        if (optUsuario.isPresent()) {
            model.addAttribute("usuario", optUsuario.get());
            model.addAttribute("isPremium", optUsuario.get().getRol() == Rol.OPOPREMIUM);
        }

        return "perfil";
    }

    /**
     * Suscribe al usuario a premium
     */
    @PostMapping("/perfil/suscribirse")
    public String suscribirse(
            Authentication authentication,
            Model model) {

        String email = authentication.getName();
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);

        if (optUsuario.isEmpty()) {
            return "redirect:/login";
        }

        Usuario usuario = optUsuario.get();
        Optional<String> error = perfilService.suscribirse(usuario.getId());

        if (error.isPresent()) {
            model.addAttribute("errorSuscripcion", error.get());
        } else {
            model.addAttribute("successSuscripcion", "¡Bienvenido a OPOPremium! Ahora tienes acceso a todas las funciones premium.");
        }

        // Recargar usuario
        optUsuario = usuarioRepository.findByEmail(email);
        if (optUsuario.isPresent()) {
            model.addAttribute("usuario", optUsuario.get());
            model.addAttribute("isPremium", optUsuario.get().getRol() == Rol.OPOPREMIUM);
        }

        return "perfil";
    }
}