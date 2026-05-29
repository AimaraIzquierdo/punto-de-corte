package com.puntodecorte.oposiciones.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "Flashcards")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flash")
    private Long id_flash;

    @Column(name = "preg_flash")
    private String pregunta;

    @Column(name = "resp_flash")
    private String respuesta;

    @ManyToOne
    @JoinColumn(name = "id_tema")
    private Tema tema;

    @ManyToOne
    @JoinColumn(name = "id_usu")
    private Usuario usuario;

    // IMPORTANTE:
    // NO pongas @Column(name="color")
    // porque tu tabla Flashcards NO tiene esa columna

    @Transient
    private String color;

    public Flashcard() {
    }

    public Flashcard(String pregunta,
                     String respuesta,
                     Tema tema,
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

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}