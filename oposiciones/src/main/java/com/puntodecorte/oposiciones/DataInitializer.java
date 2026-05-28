package com.puntodecorte.oposiciones;

import com.puntodecorte.oposiciones.Dominio.Rol;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public DataInitializer(UsuarioRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        crearAdminSiNoExiste("mateomorace@escuelaces.net", "Mateo", "ADMIN123");
    }

    private void crearAdminSiNoExiste(String email, String nombre, String pass) {

        if (repo.findByEmail(email).isEmpty()) {

            Usuario admin = new Usuario();
            admin.setNombre(nombre);
            admin.setApellidos("Admin");
            admin.setEmail(email);
            admin.setPassword(encoder.encode(pass));
            admin.setRol(Rol.ADMIN);
            admin.setFechaRegistro(LocalDate.now());
            admin.setEspacioAlmacenar(5000);

            repo.save(admin);
        }
    }
}