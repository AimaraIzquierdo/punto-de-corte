package com.puntodecorte.oposiciones.Dominio;

import jakarta.persistence.*;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 1000)
    private String url;

    public Video() {}

    public Video(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}