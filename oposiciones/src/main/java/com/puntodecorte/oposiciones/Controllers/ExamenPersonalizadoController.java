package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.PreguntaPersonalizada;
import com.puntodecorte.oposiciones.Service.ExamenPersonalizadoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExamenPersonalizadoController {

    private final ExamenPersonalizadoService service;

    public ExamenPersonalizadoController(ExamenPersonalizadoService service) {
        this.service = service;
    }

    @GetMapping("/simuladorExamen")
    public String simulador(Model model) {
        model.addAttribute("contador", service.getContador());
        return "simuladorExamen";
    }

    @PostMapping("/pregunta/guardar")
    public String guardarPregunta(@ModelAttribute PreguntaPersonalizada pregunta) {

        service.guardarPregunta(pregunta);

        if (service.examenListo()) {
            return "redirect:/examenPersonalizado";
        }

        return "redirect:/simuladorExamen";
    }

    @GetMapping("/examenPersonalizado")
    public String examen(Model model, HttpSession session) {

        List<PreguntaPersonalizada> examen = service.generarExamen();

        session.setAttribute("examenActual", examen);
        model.addAttribute("examen", examen);

        return "examenPersonalizadoGenerado";
    }

    @PostMapping("/personalizado/corregir")
    public String corregir(HttpServletRequest request,
                           Model model,
                           HttpSession session) {

        List<PreguntaPersonalizada> examen =
                (List<PreguntaPersonalizada>) session.getAttribute("examenActual");

        if (examen == null) {
            return "redirect:/simuladorExamen";
        }

        int aciertos = 0;
        boolean incompleto = false;

        for (PreguntaPersonalizada p : examen) {

            String resp = request.getParameter("p" + p.getIdPregunta());

            if (resp == null || resp.isEmpty()) {
                incompleto = true;
            } else if (Integer.parseInt(resp) == p.getCorrecta()) {
                aciertos++;
            }
        }

        if (incompleto) {
            model.addAttribute("error", "Debes contestar todas las preguntas");
            model.addAttribute("examen", examen);
            return "examenPersonalizadoGenerado";
        }

        model.addAttribute("aciertos", aciertos);
        model.addAttribute("examen", examen);

        session.removeAttribute("examenActual");

        return "resultadoPersonalizado";
    }
}