package com.puntodecorte.oposiciones.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "Tienda")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produc")
    private Long idProduc;

    @Column(name = "nombre_produc")
    private String nombreProduc;

    @Column(name = "descrip_produc", columnDefinition = "TEXT")
    private String descripProduc;

    @Column(name = "precio")
    private Float precio;

    @Column(name = "url_produc")
    private String urlProduc;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "id_admin")
    private Long idAdmin;

    public Producto() {}

    public Long getIdProduc() { return idProduc; }

    public String getNombreProduc() { return nombreProduc; }
    public void setNombreProduc(String nombreProduc) { this.nombreProduc = nombreProduc; }

    public String getDescripProduc() { return descripProduc; }
    public void setDescripProduc(String descripProduc) { this.descripProduc = descripProduc; }

    public Float getPrecio() { return precio; }
    public void setPrecio(Float precio) { this.precio = precio; }

    public String getUrlProduc() { return urlProduc; }
    public void setUrlProduc(String urlProduc) { this.urlProduc = urlProduc; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Long getIdAdmin() { return idAdmin; }
    public void setIdAdmin(Long idAdmin) { this.idAdmin = idAdmin; }
}