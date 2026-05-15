package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.misArchivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface misArchivosRepository extends JpaRepository<misArchivos, Long> {

}