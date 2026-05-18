package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.misArchivos;
import com.puntodecorte.oposiciones.Service.misArchivosService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MisArchivosController {

    private final misArchivosService archivoService;

    public MisArchivosController(misArchivosService archivoService) {
        this.archivoService = archivoService;
    }

    @GetMapping("/archivos")
    public String verArchivos(Model model) {
        model.addAttribute("archivos", archivoService.listarArchivos());
        return "misArchivos";
    }

    @PostMapping("/subir")
    public String subirArchivo(@RequestParam("file") MultipartFile file) throws IOException {
        archivoService.subirArchivo(file);
        return "redirect:/archivos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarArchivo(@PathVariable Long id) throws IOException {
        archivoService.borrarArchivo(id);
        return "redirect:/archivos";
    }

    @GetMapping("/ver/{id}")
    @ResponseBody
    public ResponseEntity<Resource> verArchivo(@PathVariable Long id) throws IOException {

        misArchivos archivo = archivoService.obtenerArchivo(id);

        Path path = Paths.get(archivo.getRuta());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + archivo.getNombre() + "\"")
                .contentType(MediaType.parseMediaType(archivo.getTipo()))
                .body(resource);
    }
}
