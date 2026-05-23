package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Rol;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            @ModelAttribute Usuario usuario) {

        usuario.setPassword(
                passwordEncoder.encode(
                        usuario.getPassword()
                )
        );

        usuario.setRol(Rol.OPOFREE);

        usuarioRepository.save(usuario);

        return "redirect:/login";
    }
}