package com.puntodecorte.oposiciones.Security;

import com.puntodecorte.oposiciones.Service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioService usuarioService;
    private final CustomAuthFailureHandler failureHandler;

    public SecurityConfig(
            UsuarioService usuarioService,
            CustomAuthFailureHandler failureHandler) {
        this.usuarioService = usuarioService;
        this.failureHandler = failureHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(passwordEncoder());

        // Permite diferenciar usuario inexistente de contraseña incorrecta
        authProvider.setHideUserNotFoundExceptions(false);

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/login",
                        "/registro",
                        "/css/**",
                        "/img/**",
                        "/js/**",
                        "/uploads/**",
                        "/h2-console/**"
                ).permitAll()
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

        // Solo deshabilitar CSRF para H2
        http.csrf(csrf ->
                csrf.ignoringRequestMatchers("/h2-console/**"));

        http.headers(headers ->
                headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}