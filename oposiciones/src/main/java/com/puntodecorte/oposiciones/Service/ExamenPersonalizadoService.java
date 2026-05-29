package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.PreguntaPersonalizada;
import com.puntodecorte.oposiciones.Repository.PreguntaPersonalizadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class ExamenPersonalizadoService {

    private final PreguntaPersonalizadaRepository preguntaRepo;

    private final List<PreguntaPersonalizada> buffer = new ArrayList<>();

    public ExamenPersonalizadoService(PreguntaPersonalizadaRepository preguntaRepo) {
        this.preguntaRepo = preguntaRepo;
    }

    public void guardarPregunta(PreguntaPersonalizada pregunta) {
        buffer.add(pregunta);
        preguntaRepo.save(pregunta);
    }

    public int getContador() {
        return buffer.size();
    }

    public boolean examenListo() {
        return buffer.size() >= 10;
    }

    public List<PreguntaPersonalizada> generarExamen() {
        List<PreguntaPersonalizada> examen = new ArrayList<>(buffer);
        buffer.clear();
        return examen;
    }
}