package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.PreguntaOficial;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamenOficialController {

    @GetMapping("/examenesOficiales")
    public String vista() {
        return "examenesOficiales";
    }

    @GetMapping("/oficial/generar/{categoria}")
    public String generar(@PathVariable String categoria, Model model) {

        List<PreguntaOficial> preguntas = generarPreguntas(categoria);

        model.addAttribute("preguntas", preguntas);
        model.addAttribute("categoria", categoria);

        return "examenOficialGenerado";
    }

    @PostMapping("/oficial/corregir")
    public String corregir(@RequestParam String categoria,
                           HttpServletRequest request,
                           Model model) {

        List<PreguntaOficial> preguntas = generarPreguntas(categoria);

        int aciertos = 0;
        boolean incompleto = false;

        for (int i = 0; i < preguntas.size(); i++) {

            String resp = request.getParameter("p" + i);

            if (resp == null || resp.isEmpty()) {
                incompleto = true;
            } else {
                if (Integer.parseInt(resp) == preguntas.get(i).getCorrecta()) {
                    aciertos++;
                }
            }
        }

        if (incompleto) {
            model.addAttribute("error", "Debes contestar todas las preguntas");
            model.addAttribute("preguntas", preguntas);
            model.addAttribute("categoria", categoria);
            return "examenOficialGenerado";
        }

        model.addAttribute("aciertos", aciertos);
        model.addAttribute("preguntas", preguntas);

        return "resultadoOficial";
    }

    private List<PreguntaOficial> generarPreguntas(String categoria) {

        List<PreguntaOficial> preguntas = new ArrayList<>();

        // =========================
        // DERECHO PENAL
        // =========================
        if (categoria.equals("penal")) {

            preguntas.add(crear("¿Qué es el dolo eventual?",
                    "No quiere resultado",
                    "Acepta posible resultado",
                    "No existe",
                    "Solo imprudencia",
                    2));

            preguntas.add(crear("¿Qué es la tentativa inidónea?",
                    "Desistimiento voluntario",
                    "Imposible consumación",
                    "Agravada",
                    "Solo delitos graves",
                    2));

            preguntas.add(crear("¿Qué es el concurso ideal de delitos?",
                    "Varios hechos",
                    "Un hecho varias normas",
                    "Acumulación penas",
                    "Reiteración",
                    2));

            preguntas.add(crear("Pena máxima ordinaria en España",
                    "20 años",
                    "25 años",
                    "30 años",
                    "40 años",
                    3));

            preguntas.add(crear("Legítima defensa requiere",
                    "Agresión y necesidad",
                    "Solo agresión",
                    "Huida imposible",
                    "Fuerza máxima",
                    1));

            preguntas.add(crear("Arrepentimiento espontáneo",
                    "Confesar antes descubrimiento",
                    "Perdón en juicio",
                    "Reparar daño",
                    "Después sentencia",
                    1));

            preguntas.add(crear("Homicidio doloso",
                    "Conducta + resultado + nexo causal",
                    "Solo intención",
                    "Solo resultado",
                    "Solo dolo",
                    1));

            preguntas.add(crear("Coautoría vs autoría mediata",
                    "Acuerdo previo vs instrumento",
                    "Iguales",
                    "Sin diferencia",
                    "Aleatorio",
                    1));

            preguntas.add(crear("Estado de necesidad",
                    "Peligro actual y mal menor",
                    "Solo mal menor",
                    "Siempre",
                    "Nunca",
                    1));

            preguntas.add(crear("Personas jurídicas",
                    "Multa y sanciones",
                    "Solo cárcel",
                    "Nada",
                    "Advertencia",
                    1));
        }

        // =========================
        // CUERPOS DE SEGURIDAD
        // =========================
        if (categoria.equals("seguridad")) {

            preguntas.add(crear("¿Qué ley regula las FCSE?",
                    "LO 1/1979",
                    "LO 10/1995",
                    "LO 4/2000",
                    "LO 2/1986",
                    4));

            preguntas.add(crear("¿Qué cuerpos integran las FCSE?",
                    "PN y GC",
                    "PN, GC y Local",
                    "GC y Ejército",
                    "Todos",
                    1));

            preguntas.add(crear("Principio uso de la fuerza",
                    "Congruencia",
                    "Oportunidad",
                    "Jerarquía",
                    "Proporcionalidad formal",
                    1));

            preguntas.add(crear("Detención máxima",
                    "24h",
                    "48h",
                    "72h ampliable",
                    "72h siempre",
                    3));

            preguntas.add(crear("Derechos del detenido",
                    "Asistencia letrada y derechos CE",
                    "Solo silencio",
                    "Solo intérprete",
                    "Solo fianza",
                    1));

            preguntas.add(crear("Valor del atestado policial",
                    "Valor denuncia",
                    "Prueba plena",
                    "Sentencia",
                    "Nulo",
                    1));

            preguntas.add(crear("Guardia Civil competencia",
                    "Tráfico y fiscal",
                    "Todo",
                    "Solo ciudad",
                    "Nada",
                    1));

            preguntas.add(crear("Policía judicial",
                    "Auxilio jueces",
                    "Cuerpo independiente",
                    "Solo UCO",
                    "Solo AN",
                    1));

            preguntas.add(crear("Policía autonómica vs local",
                    "Competencias distintas",
                    "Iguales",
                    "Sin función",
                    "Ejército",
                    1));

            preguntas.add(crear("Mando Policía Nacional",
                    "Director General",
                    "Presidente",
                    "Ministro",
                    "Alcalde",
                    1));
        }

        return preguntas;
    }

    private PreguntaOficial crear(String p, String o1, String o2, String o3, String o4, int c) {
        PreguntaOficial q = new PreguntaOficial();
        q.setPregunta(p);
        q.setOpcion1(o1);
        q.setOpcion2(o2);
        q.setOpcion3(o3);
        q.setOpcion4(o4);
        q.setCorrecta(c);
        return q;
    }
}