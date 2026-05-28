package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.CategoriaExamen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaExamenRepository
        extends JpaRepository<CategoriaExamen, Long> {
}