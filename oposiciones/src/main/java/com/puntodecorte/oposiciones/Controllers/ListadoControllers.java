package com.puntodecorte.oposiciones.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListadoControllers {
    @RequestMapping ("/")
    public String listarModulos(){//Cuando el ordenador acceda a la URL se activa este método, que redirigirá a la página de listado.
        //Busqueda de los módulos accesibles
        return "Listado";
    }
}
