package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Temario;
import com.puntodecorte.oposiciones.Service.TemarioService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
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

    public TemarioController(TemarioService temarioService) {
        this.temarioService = temarioService;
    }

    @GetMapping("/temario")
    public String verTemario(Model model) {

        model.addAttribute("archivos",
                temarioService.listarArchivos());

        return "temario";
    }

    @PostMapping("/subir-temario")
    public String subirArchivo(@RequestParam("file") MultipartFile file,
                               @RequestParam("oposicion") String oposicion)
            throws IOException {

        temarioService.subirArchivo(file, oposicion);

        return "redirect:/temario";
    }

    @GetMapping("/ver-temario/{id}")
    @ResponseBody
    public ResponseEntity<Resource> verArchivo(@PathVariable Long id)
            throws IOException {

        Temario archivo = temarioService.obtenerArchivo(id);

        Path path = Paths.get(archivo.getRuta());

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" +
                                archivo.getNombre() + "\"")
                .contentType(MediaType.parseMediaType(archivo.getTipo()))
                .body(resource);
    }

    @GetMapping("/eliminar-temario/{id}")
    public String eliminarArchivo(@PathVariable Long id)
            throws IOException {

        temarioService.borrarArchivo(id);

        return "redirect:/temario";
    }
}