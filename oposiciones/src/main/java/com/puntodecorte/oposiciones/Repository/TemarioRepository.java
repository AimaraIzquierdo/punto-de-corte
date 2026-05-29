package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.Temario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemarioRepository extends JpaRepository<Temario, Long> {

}

