package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Calendario;
import com.puntodecorte.oposiciones.Repository.CalendarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioService {

    private final CalendarioRepository repo;

    public CalendarioService(CalendarioRepository repo) {
        this.repo = repo;
    }

    public List<Calendario> listar() {
        return repo.findAll();
    }

    public Calendario guardar(Calendario c) {
        return repo.save(c);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}