package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository
        extends JpaRepository<Tema, Long> {
}