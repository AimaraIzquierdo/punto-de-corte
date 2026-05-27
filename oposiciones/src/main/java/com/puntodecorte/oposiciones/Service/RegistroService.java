package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Rol;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class RegistroService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Intenta registrar un usuario. Devuelve Optional.empty() si registro correcto,
     * o Optional.of(mensajeError) si hubo algún problema.
     */
    public Optional<String> registrarUsuario(String nombre,
                                             String apellidos,
                                             String emailRaw,
                                             String password,
                                             String passwordConfirm) {

        // Normalizar email
        if (emailRaw == null) return Optional.of("Email inválido");
        String email = emailRaw.trim().toLowerCase();

        // Validaciones básicas
        if (nombre == null || nombre.trim().isEmpty()
                || apellidos == null || apellidos.trim().isEmpty()
                || email.isEmpty()
                || password == null || password.isEmpty()
                || passwordConfirm == null || passwordConfirm.isEmpty()) {
            return Optional.of("Todos los campos son requeridos");
        }

        // Validar formato email (simple)
        final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        if (!emailPattern.matcher(email).matches()) {
            return Optional.of("Formato de email inválido");
        }

        // Validar contraseñas coinciden
        if (!password.equals(passwordConfirm)) {
            return Optional.of("Las contraseñas no coinciden");
        }

        // Validar complejidad de contraseña: min 10 chars, 1 mayúscula, 1 minúscula, 1 dígito, 1 especial
        final Pattern pwdPattern = Pattern.compile(
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*()_+\\-]).{10,}$"
        );
        if (!pwdPattern.matcher(password).matches()) {
            return Optional.of("La contraseña debe tener al menos 10 caracteres, una mayúscula, una minúscula, un número y un carácter especial");
        }

        // Comprobar existencia del email
        if (usuarioRepository.findByEmail(email).isPresent()) {
            return Optional.of("Usuario ya registrado");
        }

        // Crear usuario y persistir
        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre.trim());
        nuevo.setApellidos(apellidos.trim());
        nuevo.setEmail(email);
        nuevo.setPassword(passwordEncoder.encode(password)); // BCrypt
        nuevo.setRol(Rol.OPOFREE); // rol por defecto
        nuevo.setFechaRegistro(LocalDate.now());
        nuevo.setEspacioAlmacenar(Integer.valueOf(5000)); // 5GB por defecto
        // Si Usuario tiene otros campos (telefono, foto...), se pueden dejar nulos.

        usuarioRepository.save(nuevo);

        return Optional.empty(); // OK
    }
}