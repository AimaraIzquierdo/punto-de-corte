package com.puntodecorte.oposiciones.Dominio;

public class Video {

    private String titulo;
    private String url;

    public Video() {
    }

    public Video(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
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
