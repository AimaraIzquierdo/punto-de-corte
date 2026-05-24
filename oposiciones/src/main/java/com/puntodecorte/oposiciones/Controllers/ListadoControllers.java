package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Modulo;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Service.ModuloService;
import com.puntodecorte.oposiciones.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ListadoControllers {

    private final ModuloService moduloService;
    private final UsuarioService usuarioService;

    public ListadoControllers(ModuloService moduloService, UsuarioService usuarioService) {
        this.moduloService = moduloService;
        this.usuarioService = usuarioService;
    }

    /* ================= HOME ================= */

    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String listarModulos(Model model){

        List<Modulo> listaModulos = moduloService.listarModulos();
        Usuario usuario = usuarioService.obtenerUsuarioActual();
        model.addAttribute("modulos", listaModulos);
        model.addAttribute("usuario", usuario);

        return "Listado";
    }

    /* ================= SUSHITO IA ================= */

    @GetMapping("/sushito")
    public String mostrarSushito(){
        return "sushito";
    }
}