package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Rol;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class RegistroController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroController(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String nombre,
            @RequestParam String apellidos,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String passwordConfirm,
            Model model) {

        // Validación 1: campos vacíos
        if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            model.addAttribute("error", "Todos los campos son requeridos");
            return "registro";
        }

        // Validación 2: contraseñas coinciden
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "registro";
        }

        // Validación 3: usuario ya existe
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);
        if (usuarioExistente.isPresent()) {
            model.addAttribute("error", "Usuario ya registrado");
            return "registro";
        }

        // Crear nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellidos(apellidos);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setRol(Rol.OPOFREE);
        nuevoUsuario.setFechaRegistro(LocalDate.now());
        nuevoUsuario.setEspacioAlmacenar(5000); // 5GB por defecto

        usuarioRepository.save(nuevoUsuario);

        // Mostrar mensaje de éxito (opcional: lo puedes sacar si quieres ir directo a login)
        model.addAttribute("success", "¡Cuenta creada exitosamente! Inicia sesión");
        return "registro";
    }
}