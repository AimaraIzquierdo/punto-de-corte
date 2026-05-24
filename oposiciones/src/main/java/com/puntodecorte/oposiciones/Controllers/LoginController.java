package com.puntodecorte.oposiciones.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        // Leer mensaje del handler si existe
        if (request.getSession(false) != null) {
            String loginError = (String) request.getSession().getAttribute("loginError");
            if (loginError != null) {
                model.addAttribute("error", loginError);
                request.getSession().removeAttribute("loginError");
            }
        }
        return "login";
    }
}
