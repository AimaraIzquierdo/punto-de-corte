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

    @GetMapping("/ampliado/{tipo}")
    public String verAmpliado(@PathVariable("tipo") String tipo, Model model) {

        List<Estadistica> lista = service.listar();

        // Filtrar por tipo
        List<Estadistica> filtrados = lista.stream()
                .filter(e -> e.getTipo().name().equals(tipo))
                .toList();

        model.addAttribute("estadisticas", filtrados);
        model.addAttribute("tipo", tipo);
        model.addAttribute("tipoNombre", tipo.equals("TEORIA") ? "Teoría" :
                tipo.equals("PRACTICA") ? "Práctica" : "Otros");

        return "estadisticaAmpliada";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String titulo,
                          @RequestParam String materia,
                          @RequestParam String fecha,
                          @RequestParam Double nota,
                          @RequestParam String tipo) {

        Estadistica e = new Estadistica();
        e.setTitulo(titulo);
        e.setMateria(materia);
        e.setFecha(java.time.LocalDate.parse(fecha));
        e.setNota(nota);

        // Convertir String a enum
        TipoEstadistica tipoEnum;
        try {
            tipoEnum = TipoEstadistica.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException ex) {
            tipoEnum = TipoEstadistica.OTROS;
        }
        e.setTipo(tipoEnum);

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