package com.puntodecorte.oposiciones.Dominio;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class misArchivos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String ruta;

    private String tipo;

    private Long tamano;

    private LocalDateTime fechaSubida;

    public misArchivos() {
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

    public Long getTamano() {
        return tamano;
    }

    public void setTamano(Long tamano) {
        this.tamano = tamano;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}
