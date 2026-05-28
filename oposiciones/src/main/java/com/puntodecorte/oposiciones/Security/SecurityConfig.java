package com.puntodecorte.oposiciones.Security;

import com.puntodecorte.oposiciones.Service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioService usuarioService;
    private final CustomAuthFailureHandler failureHandler;

    public SecurityConfig(UsuarioService usuarioService, CustomAuthFailureHandler failureHandler) {
        this.usuarioService = usuarioService;
        this.failureHandler = failureHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // coste 12, equilibrado entre seguridad y rendimiento
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setHideUserNotFoundExceptions(false); // importante para diferenciar usuario inexistente
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        // Si usas H2 console en desarrollo, ignorar CSRF para esa ruta y permitir frames
        http.csrf(csrf -> csrf.ignoringRequestMatchers(
                "/h2-console/**",
                "/calendario/**"
        ));
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));


        http.authorizeHttpRequests(auth -> auth
                //Permisos publicos
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/login", "/registro", "/css/**", "/img/**", "/js/**").permitAll()


                // Permisos para registrados
                .requestMatchers(
                        "/estadisticas/**",
                        "/temario/**",
                        "/perfil/**",
                        "/flashcards/**",
                        "/videos/**",
                        "/archivos/**",
                        "/simuladorExamen"
                ).hasAnyRole("OPOFREE", "OPOPREMIUM", "ACADEMIA", "OPOACADEMIA", "ADMIN")

                // Permisos opopremium
                .requestMatchers(
                        "/carrito/**",
                        "/tiendaOpositor/**"
                ).hasAnyRole("OPOFREE", "OPOPREMIUM","OPOACADEMIA", "ADMIN")

                // Permisos IA
                .requestMatchers(
                        "/sushito/**",
                        "/calendario/**",
                        "/examenesOficiales"
                ).hasAnyRole("OPOPREMIUM", "OPOACADEMIA", "ACADEMIA", "ADMIN")


                // Permisos opoacademia
                .requestMatchers(
                        "/carrito/**",
                        "/tienda/**"
                ).hasAnyRole("OPOACADEMIA", "ACADEMIA", "ADMIN")


                // Permisos academias
                .requestMatchers(
                        "/tienda/admin/**"
                ).hasAnyRole("ACADEMIA", "ADMIN")

                // Permisos administradores
                .requestMatchers("/admin/**").hasRole("ADMIN")

                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureHandler(failureHandler)
                .defaultSuccessUrl("/home", true)
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        );

        return http.build();
    }
}