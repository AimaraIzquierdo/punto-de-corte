package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.Flashcard;
import com.puntodecorte.oposiciones.Dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashcardRepository
        extends JpaRepository<Flashcard, Long> {

    List<Flashcard> findByUsuario(Usuario usuario);

}