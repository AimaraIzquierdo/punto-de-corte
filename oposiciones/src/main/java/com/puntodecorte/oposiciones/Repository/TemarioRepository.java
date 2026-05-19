package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.Temario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemarioRepository
        extends JpaRepository<Temario, Long> {

    List<Temario> findByOposicion(String oposicion);
}
