package com.puntodecorte.oposiciones.Dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Temario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String ruta;

    private String tipo;

    private String oposicion;

    private LocalDateTime fechaSubida;

    public Temario() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOposicion() {
        return oposicion;
    }

    public void setOposicion(String oposicion) {
        this.oposicion = oposicion;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}