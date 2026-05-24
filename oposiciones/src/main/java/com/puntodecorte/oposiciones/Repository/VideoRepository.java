package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}