package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.PreguntaPersonalizada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaPersonalizadaRepository
        extends JpaRepository<PreguntaPersonalizada, Long> {
}