package com.puntodecorte.oposiciones.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "preguntas_personalizadas")
public class PreguntaPersonalizada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregunta;

    private String pregunta;

    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;

    private Integer correcta;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaExamen categoria;

    public PreguntaPersonalizada() {
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public Integer getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Integer correcta) {
        this.correcta = correcta;
    }

    public CategoriaExamen getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaExamen categoria) {
        this.categoria = categoria;
    }
}