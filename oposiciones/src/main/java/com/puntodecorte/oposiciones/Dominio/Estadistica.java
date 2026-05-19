package com.puntodecorte.oposiciones.Dominio;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Estadistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String materia;
    private LocalDate fecha;
    private Double nota;

    @Enumerated(EnumType.STRING)
    private TipoEstadistica tipo;


    public Estadistica() {}

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public TipoEstadistica getTipo() {
        return tipo;
    }

    public void setTipo(TipoEstadistica tipo) {
        this.tipo = tipo;
    }
}