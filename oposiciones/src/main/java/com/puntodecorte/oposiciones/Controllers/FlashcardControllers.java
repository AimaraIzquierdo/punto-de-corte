package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Flashcard;
import com.puntodecorte.oposiciones.Dominio.Tema;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Repository.TemaRepository;
import com.puntodecorte.oposiciones.Service.FlashcardService;
import com.puntodecorte.oposiciones.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlashcardControllers {

    private final FlashcardService flashcardService;
    private final UsuarioService usuarioService;
    private final TemaRepository temaRepository;

    public FlashcardControllers(
            FlashcardService flashcardService,
            UsuarioService usuarioService,
            TemaRepository temaRepository) {

        this.flashcardService = flashcardService;
        this.usuarioService = usuarioService;
        this.temaRepository = temaRepository;
    }

    @GetMapping("/flashcards")
    public String flashcards(Model model) {

        Usuario usuario =
                usuarioService.obtenerUsuarioActual();

        model.addAttribute(
                "flashcards",
                flashcardService.obtenerPorUsuario(usuario));

        model.addAttribute(
                "temas",
                temaRepository.findAll());


        return "flashcards";
    }
    @PostMapping("/flashcards/guardar")
    public String guardarFlashcard(
            @RequestParam String pregunta,
            @RequestParam String respuesta,
            @RequestParam Long idTema) {

        Usuario usuario = usuarioService.obtenerUsuarioActual();

        Tema tema = temaRepository.findById(idTema)
                .orElseThrow();

        Flashcard flashcard = new Flashcard();

        flashcard.setPregunta(pregunta);
        flashcard.setRespuesta(respuesta);
        flashcard.setTema(tema);
        flashcard.setUsuario(usuario);

        flashcardService.guardar(flashcard);

        return "redirect:/flashcards";
    }
}