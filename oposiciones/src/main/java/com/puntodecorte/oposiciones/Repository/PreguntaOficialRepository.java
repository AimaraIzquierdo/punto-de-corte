package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.PreguntaOficial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PreguntaOficialRepository
        extends JpaRepository<PreguntaOficial, Long> {

    @Query(
            value = """
                    SELECT *
                    FROM preguntas_oficiales
                    WHERE id_examen = ?1
                    ORDER BY RAND()
                    LIMIT 10
                    """,
            nativeQuery = true
    )
    List<PreguntaOficial> obtener10Preguntas(Long idExamen);
}