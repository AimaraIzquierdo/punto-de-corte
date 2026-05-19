package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Temario;
import com.puntodecorte.oposiciones.Service.TemarioService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class TemarioController {

    private final TemarioService temarioService;

    public TemarioController(
            TemarioService temarioService) {

        this.temarioService = temarioService;
    }

    @GetMapping("/temarios")
    public String verTemarios(Model model) {

        model.addAttribute(
                "guardiaCivil",
                temarioService.listarPorOposicion(
                        "Guardia Civil"
                )
        );

        model.addAttribute(
                "bomberos",
                temarioService.listarPorOposicion(
                        "Bomberos Madrid"
                )
        );

        return "temarios";
    }

    @PostMapping("/temarios/subir")
    public String subirTemario(

            @RequestParam("file")
            MultipartFile file,

            @RequestParam("oposicion")
            String oposicion)

            throws IOException {

        temarioService.subirTemario(
                file,
                oposicion
        );

        return "redirect:/temarios";
    }

    @GetMapping("/temarios/eliminar/{id}")
    public String eliminarTemario(
            @PathVariable Long id)

            throws IOException {

        temarioService.borrarTemario(id);

        return "redirect:/temarios";
    }

    @GetMapping("/temarios/ver/{id}")
    @ResponseBody
    public Resource verTemario(
            @PathVariable Long id)

            throws IOException {

        Temario temario =
                temarioService.obtenerTemario(id);

        Path path =
                Paths.get(temario.getRuta());

        return new UrlResource(path.toUri());
    }
}