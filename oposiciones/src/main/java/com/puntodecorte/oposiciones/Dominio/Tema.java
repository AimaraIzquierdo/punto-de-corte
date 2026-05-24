package com.puntodecorte.oposiciones.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "Temas")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tema")
    private Long id;

    @Column(name = "nombre_tema")
    private String nombre;

    @Column(name = "desc_tema")
    private String descripcion;

    public Tema() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}