package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.CategoriaExamen;
import com.puntodecorte.oposiciones.Dominio.PreguntaPersonalizada;
import com.puntodecorte.oposiciones.Repository.CategoriaExamenRepository;
import com.puntodecorte.oposiciones.Repository.PreguntaPersonalizadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class ExamenPersonalizadoService {

    private final CategoriaExamenRepository categoriaRepo;
    private final PreguntaPersonalizadaRepository preguntaRepo;

    private final List<PreguntaPersonalizada> buffer = new ArrayList<>();
    private CategoriaExamen categoriaSeleccionada;

    public ExamenPersonalizadoService(CategoriaExamenRepository categoriaRepo,
                                      PreguntaPersonalizadaRepository preguntaRepo) {
        this.categoriaRepo = categoriaRepo;
        this.preguntaRepo = preguntaRepo;
    }

    public List<CategoriaExamen> listarCategorias() {
        return categoriaRepo.findAll();
    }

    public void setCategoria(CategoriaExamen categoria) {
        this.categoriaSeleccionada = categoria;
    }

    public void guardarPregunta(PreguntaPersonalizada pregunta) {

        if (categoriaSeleccionada != null) {
            pregunta.setCategoria(categoriaSeleccionada);
        }

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
        categoriaSeleccionada = null;
        return examen;
    }
    // Devuelve el buffer SIN limpiarlo (para usar en corrección)
    public List<PreguntaPersonalizada> obtenerBuffer() {
        return new ArrayList<>(buffer);
    }
}