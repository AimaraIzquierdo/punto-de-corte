package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.CategoriaExamen;
import com.puntodecorte.oposiciones.Dominio.PreguntaPersonalizada;
import com.puntodecorte.oposiciones.Service.ExamenPersonalizadoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamenPersonalizadoController {

    private final ExamenPersonalizadoService service;

    public ExamenPersonalizadoController(ExamenPersonalizadoService service) {
        this.service = service;
    }

    // VISTA CREACIÓN
    @GetMapping("/simuladorExamen")
    public String simulador(Model model) {

        model.addAttribute("categorias", service.listarCategorias());
        model.addAttribute("contador", service.getContador());

        return "simuladorExamen";
    }

    // FIJAR CATEGORÍA
    @PostMapping("/categoria/guardar")
    public String guardarCategoria(@RequestParam Long idCategoria) {

        CategoriaExamen cat = new CategoriaExamen();
        cat.setIdCategoria(idCategoria);

        service.setCategoria(cat);

        return "redirect:/simuladorExamen";
    }

    // GUARDAR PREGUNTA
    @PostMapping("/pregunta/guardar")
    public String guardarPregunta(@ModelAttribute PreguntaPersonalizada pregunta) {

        service.guardarPregunta(pregunta);

        if (service.examenListo()) {
            return "redirect:/examenPersonalizado";
        }

        return "redirect:/simuladorExamen";
    }

    // EXAMEN
    @GetMapping("/examenPersonalizado")
    public String examen(Model model, HttpSession session) {
        List<PreguntaPersonalizada> examen = service.generarExamen();
        session.setAttribute("examenActual", examen);  // ← Guardar en sesión
        model.addAttribute("examen", examen);
        return "examenPersonalizadoGenerado";
    }

    // CORRECCIÓN (ARREGLADO DEFINITIVO)
    @PostMapping("/personalizado/corregir")
    public String corregir(HttpServletRequest request, Model model, HttpSession session) {

        // Recuperar examen de la sesión (no del buffer vacío)
        List<PreguntaPersonalizada> examen = (List<PreguntaPersonalizada>) session.getAttribute("examenActual");

        if (examen == null || examen.isEmpty()) {
            model.addAttribute("error", "Sesión expirada o examen no encontrado");
            return "redirect:/simuladorExamen";
        }

        int aciertos = 0;
        boolean incompleto = false;

        for (PreguntaPersonalizada p : examen) {
            String resp = request.getParameter("p" + p.getIdPregunta());

            if (resp == null || resp.isEmpty()) {
                incompleto = true;
            } else {
                if (Integer.parseInt(resp) == p.getCorrecta()) {
                    aciertos++;
                }
            }
        }

        if (incompleto) {
            model.addAttribute("error", "Debes contestar todas las preguntas");
            model.addAttribute("examen", examen);
            return "examenPersonalizadoGenerado";
        }

        model.addAttribute("aciertos", aciertos);
        model.addAttribute("examen", examen);

        session.removeAttribute("examenActual");  // Limpiar sesión

        return "resultadoPersonalizado";
    }
}