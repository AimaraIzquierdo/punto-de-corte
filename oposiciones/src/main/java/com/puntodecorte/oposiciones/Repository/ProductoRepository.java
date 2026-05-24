package com.puntodecorte.oposiciones.Repository;

import com.puntodecorte.oposiciones.Dominio.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(String categoria);
}