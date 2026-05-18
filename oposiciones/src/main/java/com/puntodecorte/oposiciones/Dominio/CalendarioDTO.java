package com.puntodecorte.oposiciones.Dominio;

public class CalendarioDTO {

    private Long id;
    private String title;
    private String start;
    private String end;
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getStart() { return start; }
    public void setStart(String start) { this.start = start; }

    public String getEnd() { return end; }
    public void setEnd(String end) { this.end = end; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}