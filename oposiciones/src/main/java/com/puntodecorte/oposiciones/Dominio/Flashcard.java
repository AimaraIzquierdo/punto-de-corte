package com.puntodecorte.oposiciones.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "Flashcards")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_flash;

    private String pregunta;

    private String respuesta;

    private String tema;

    private String color;

    public Flashcard() {
    }

    public Flashcard(String pregunta,
                     String respuesta,
                     String tema,
                     String color) {

        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.tema = tema;
        this.color = color;
    }

    public Long getId_flash() {
        return id_flash;
    }

    public void setId_flash(Long id_flash) {
        this.id_flash = id_flash;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
