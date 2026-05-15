package com.puntodecorte.oposiciones.Service;

import org.springframework.stereotype.Service;

@Service
public class SushitoService {

    public String resumirTemario(String texto) {

        // PROMPT BASE (Sushito Comunica)
        String prompt =
                "Eres Sushito Comunica, experto en oposiciones en España.\n" +
                        "Tu función es resumir temarios de forma clara, estructurada y fácil de estudiar.\n" +
                        "No inventes información.\n\n" +
                        "TEXTO:\n" + texto;

        // SIMULACIÓN (luego conectas OpenAI aquí)
        return "📌 RESUMEN:\n\n" +
                texto.substring(0, Math.min(400, texto.length()));
    }
}