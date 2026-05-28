package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.PreguntaOficial;
import com.puntodecorte.oposiciones.Repository.PreguntaOficialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenOficialService {

    private final PreguntaOficialRepository preguntaRepo;

    public ExamenOficialService(
            PreguntaOficialRepository preguntaRepo) {

        this.preguntaRepo = preguntaRepo;
    }

    public List<PreguntaOficial> generarExamen(
            Long idExamen){

        return preguntaRepo.obtener10Preguntas(idExamen);
    }
}