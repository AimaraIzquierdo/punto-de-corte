package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Rol;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PerfilService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public PerfilService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Actualiza datos básicos del usuario (nombre, apellidos, teléfono, etc.)
     */
    public Optional<String> actualizarDatos(Long usuarioId,
                                            String nombre,
                                            String apellidos,
                                            String telefono) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioId);
        if (optUsuario.isEmpty()) {
            return Optional.of("Usuario no encontrado");
        }

        Usuario usuario = optUsuario.get();

        if (nombre == null || nombre.trim().isEmpty()) {
            return Optional.of("El nombre no puede estar vacío");
        }
        if (apellidos == null || apellidos.trim().isEmpty()) {
            return Optional.of("Los apellidos no pueden estar vacíos");
        }

        usuario.setNombre(nombre.trim());
        usuario.setApellidos(apellidos.trim());
        if (telefono != null && !telefono.trim().isEmpty()) {
            // Validar formato básico de teléfono (9-15 dígitos, opcional + al inicio)
            if (!telefono.matches("^\\+?\\d{9,15}$")) {
                return Optional.of("Formato de teléfono inválido");
            }
            usuario.setTelefono(telefono.trim());
        }

        usuarioRepository.save(usuario);
        return Optional.empty();
    }

    /**
     * Cambia la contraseña del usuario
     */
    public Optional<String> cambiarContrasena(Long usuarioId,
                                              String passwordActual,
                                              String passwordNueva,
                                              String passwordConfirm) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioId);
        if (optUsuario.isEmpty()) {
            return Optional.of("Usuario no encontrado");
        }

        Usuario usuario = optUsuario.get();

        // Validar contraseña actual
        if (!passwordEncoder.matches(passwordActual, usuario.getPassword())) {
            return Optional.of("La contraseña actual es incorrecta");
        }

        // Validar que coincida la nueva contraseña
        if (!passwordNueva.equals(passwordConfirm)) {
            return Optional.of("Las contraseñas nuevas no coinciden");
        }

        // Validar que la nueva contraseña sea diferente a la actual
        if (passwordActual.equals(passwordNueva)) {
            return Optional.of("La nueva contraseña debe ser diferente a la actual");
        }

        // Validar complejidad de nueva contraseña
        final java.util.regex.Pattern pwdPattern = java.util.regex.Pattern.compile(
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*()_+\\-]).{10,}$"
        );
        if (!pwdPattern.matcher(passwordNueva).matches()) {
            return Optional.of("La contraseña debe tener al menos 10 caracteres, una mayúscula, una minúscula, un número y un carácter especial");
        }

        usuario.setPassword(passwordEncoder.encode(passwordNueva));
        usuarioRepository.save(usuario);
        return Optional.empty();
    }

    /**
     * Suscribe un usuario a premium (cambia rol a OPOPREMIUM)
     */
    public Optional<String> suscribirse(Long usuarioId) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioId);
        if (optUsuario.isEmpty()) {
            return Optional.of("Usuario no encontrado");
        }

        Usuario usuario = optUsuario.get();

        if (usuario.getRol() == Rol.OPOPREMIUM) {
            return Optional.of("Ya eres usuario premium");
        }

        usuario.setRol(Rol.OPOPREMIUM);
        // Aumentar espacio al suscribirse
        usuario.setEspacioAlmacenar(Integer.valueOf(50000)); // 50GB

        usuarioRepository.save(usuario);
        return Optional.empty();
    }
}