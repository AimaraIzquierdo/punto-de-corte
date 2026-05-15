package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Service.SushitoService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sushito")
public class SushitoController {

    private final SushitoService sushitoService;

    public SushitoController(SushitoService sushitoService) {
        this.sushitoService = sushitoService;
    }

    @PostMapping("/resumir")
    public Map<String, String> resumir(@RequestBody Map<String, String> body) {

        String texto = body.get("texto");

        String resumen = sushitoService.resumirTemario(texto);

        return Map.of("resumen", resumen);
    }
}