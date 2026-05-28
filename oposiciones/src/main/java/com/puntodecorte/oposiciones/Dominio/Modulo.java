package com.puntodecorte.oposiciones.Dominio;

import java.util.Set;

public class Modulo {
    private String nombre;
    private String descripcion;
    private String imagen;
    private String textoBoton;
    private String ruta;

    private Set<Rol> rolesPermitidos;


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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTextoBoton() {
        return textoBoton;
    }

    public void setTextoBoton(String textoBoton) {
        this.textoBoton = textoBoton;
    }

    public String getRuta() { return ruta; }

    public void setRuta(String ruta) { this.ruta = ruta; }

    public Set<Rol> getRolesPermitidos() { return rolesPermitidos; }

    public void setRolesPermitidos(Set<Rol> rolesPermitidos) { this.rolesPermitidos = rolesPermitidos; }
}
