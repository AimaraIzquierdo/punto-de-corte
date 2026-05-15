package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
}