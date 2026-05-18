package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Estadistica;
import com.puntodecorte.oposiciones.Service.EstadisticaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.puntodecorte.oposiciones.Dominio.TipoEstadistica;

import java.util.List;

@Controller
@RequestMapping("/estadisticas")
public class EstadisticaController {

    private final EstadisticaService service;

    public EstadisticaController(EstadisticaService service) {
        this.service = service;
    }

    @GetMapping
    public String ver(Model model) {

        List<Estadistica> lista = service.listar();

        model.addAttribute("estadisticas", lista);

        return "estadisticas";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String titulo,
                          @RequestParam String materia,
                          @RequestParam String fecha,
                          @RequestParam Double nota,
                          @RequestParam TipoEstadistica tipo) {

        Estadistica e = new Estadistica();
        e.setTitulo(titulo);
        e.setMateria(materia);
        e.setFecha(java.time.LocalDate.parse(fecha));
        e.setNota(nota);
        e.setTipo(tipo);

        service.guardar(e);

        return "redirect:/estadisticas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/estadisticas";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         @RequestParam Double nota) {

        Estadistica e = service.listar()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (e != null) {
            e.setNota(nota);
            service.guardar(e);
        }

        return "redirect:/estadisticas";
    }
}