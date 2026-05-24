package com.puntodecorte.oposiciones.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String mensaje;
        if (exception instanceof UsernameNotFoundException) {
            mensaje = "No estás registrado";
        } else if (exception instanceof BadCredentialsException) {
            mensaje = "Datos de acceso erroneos";
        } else {
            mensaje = "Error en la autenticación";
        }

        // Guardar mensaje en sesión para mostrar en la página de login
        request.getSession().setAttribute("loginError", mensaje);

        // Redirigir a la página de login (GET) donde el LoginController leerá el mensaje
        response.sendRedirect("/login");
    }
}