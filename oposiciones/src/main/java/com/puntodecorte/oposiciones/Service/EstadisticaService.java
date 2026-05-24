package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Estadistica;
import com.puntodecorte.oposiciones.Repository.EstadisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadisticaService {

    @Autowired
    private final EstadisticaRepository repo;

    public EstadisticaService(EstadisticaRepository repo) {
        this.repo = repo;
    }

    public List<Estadistica> listar() {
        return repo.findAll();
    }

    public void guardar(Estadistica e) {
        repo.save(e);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}