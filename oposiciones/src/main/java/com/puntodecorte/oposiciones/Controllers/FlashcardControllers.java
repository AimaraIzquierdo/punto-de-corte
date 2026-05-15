package com.puntodecorte.oposiciones.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlashcardControllers {

    @GetMapping("/flashcards")
    public String flashcards(Model model) {

        return "flashcards";
    }
}