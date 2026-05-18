package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.Estadistica;
import com.puntodecorte.oposiciones.Dominio.TipoEstadistica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadisticaRepository extends JpaRepository<Estadistica, Long> {
    List<Estadistica> findByTipo(TipoEstadistica tipo);
}