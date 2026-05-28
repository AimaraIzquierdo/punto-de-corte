package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Service.RegistroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        // Se usan atributos 'error' y 'success' en la vista
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String nombre,
            @RequestParam String apellidos,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String passwordConfirm,
            @RequestParam(required = false) Boolean aceptaTerminos,
            Model model) {

        if (aceptaTerminos == null || !aceptaTerminos) {
            model.addAttribute("error",
                    "Debes aceptar los términos y condiciones");

            return "registro";
        }

        var resultado = registroService.registrarUsuario(nombre, apellidos, email, password, passwordConfirm);

        if (resultado.isPresent()) {
            model.addAttribute("error", resultado.get());
            return "registro";
        }

        // Registro correcto
        model.addAttribute("success", "Cuenta creada correctamente. Ahora puedes iniciar sesión.");
        return "registro"; // mostramos la misma vista con mensaje de éxito; si prefieres redirigir a /login: return "redirect:/login";
    }
}