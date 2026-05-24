package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<CarritoItem, Long> {
    List<CarritoItem> findByIdUsu(Long idUsu);
    Optional<CarritoItem> findByIdUsuAndProductoIdProduc(Long idUsu, Long idProduc);
}