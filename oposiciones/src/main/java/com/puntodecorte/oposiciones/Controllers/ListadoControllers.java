package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.modulos;
import com.puntodecorte.oposiciones.Service.modulosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ListadoControllers {

    private final modulosService modulosService;

    public ListadoControllers(modulosService modulosService) {
        this.modulosService = modulosService;
    }

    @RequestMapping ("/")
    public String listarModulos(Model model){
        List<modulos> modulos = modulosService.listarModulos();
        model.addAttribute("modulos", modulos);
        return "Listado";
    }
}
