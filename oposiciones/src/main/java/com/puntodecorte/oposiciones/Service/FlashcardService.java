package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Flashcard;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Repository.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {

    private final FlashcardRepository flashcardRepository;

    public FlashcardService(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    public List<Flashcard> obtenerPorUsuario(Usuario usuario) {
        return flashcardRepository.findByUsuario(usuario);
    }

    public Flashcard guardar(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }
}