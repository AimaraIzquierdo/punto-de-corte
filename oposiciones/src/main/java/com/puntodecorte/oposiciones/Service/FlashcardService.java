package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Flashcard;
import com.puntodecorte.oposiciones.Dominio.Tema;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import com.puntodecorte.oposiciones.Repository.FlashcardRepository;
import com.puntodecorte.oposiciones.Repository.TemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final TemaRepository temaRepository;

    public FlashcardService(FlashcardRepository flashcardRepository,
                            TemaRepository temaRepository) {
        this.flashcardRepository = flashcardRepository;
        this.temaRepository = temaRepository;
    }

    public List<Flashcard> obtenerPorUsuario(Usuario usuario) {
        return flashcardRepository.findByUsuario(usuario);
    }

    public Flashcard guardar(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }

    public void eliminarFlashcard(Long idFlash) {
        flashcardRepository.deleteById(idFlash);
    }

    public void eliminarTema(Long idTema) {

        // borrar flashcards del tema
        List<Flashcard> flashcards = flashcardRepository.findAll()
                .stream()
                .filter(f -> f.getTema() != null &&
                        f.getTema().getId().equals(idTema))
                .toList();

        flashcardRepository.deleteAll(flashcards);

        // borrar tema
        temaRepository.deleteById(idTema);
    }
}