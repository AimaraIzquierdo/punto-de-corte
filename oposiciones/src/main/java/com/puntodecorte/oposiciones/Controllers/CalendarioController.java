package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Calendario;
import com.puntodecorte.oposiciones.Dominio.CalendarioDTO;
import com.puntodecorte.oposiciones.Service.CalendarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CalendarioController {

    private final CalendarioService service;

    public CalendarioController(CalendarioService service) {
        this.service = service;
    }

    @GetMapping("/calendario")
    public String calendario() {
        return "calendario";
    }

    // 👉 eventos
    @ResponseBody
    @GetMapping("/calendario/eventos")
    public List<CalendarioDTO> eventos() {

        return service.listar().stream().map(c -> {
            CalendarioDTO dto = new CalendarioDTO();
            dto.setId(c.getId());
            dto.setTitle(c.getTitulo());
            dto.setStart(c.getFechaInicio());
            dto.setEnd(c.getFechaFin());
            dto.setDescription(c.getDescripcion());
            return dto;
        }).toList();
    }

    // 👉 crear evento
    @ResponseBody
    @PostMapping("/calendario")
    public Calendario crear(@RequestBody CalendarioDTO dto) {

        Calendario c = new Calendario();
        c.setTitulo(dto.getTitle());
        c.setFechaInicio(dto.getStart());
        c.setFechaFin(dto.getEnd());
        c.setDescripcion(dto.getDescription());

        return service.guardar(c);
    }

    // 👉 borrar
    @ResponseBody
    @DeleteMapping("/calendario/{id}")
    public void borrar(@PathVariable Long id) {
        service.eliminar(id);
    }
}