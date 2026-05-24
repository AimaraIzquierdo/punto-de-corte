package com.puntodecorte.oposiciones.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "Carrito")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @Column(name = "id_usu")
    private Long idUsu;

    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_produc")
    private Producto producto;

    public CarritoItem() {}

    public Long getIdCarrito() { return idCarrito; }

    public Long getIdUsu() { return idUsu; }
    public void setIdUsu(Long idUsu) { this.idUsu = idUsu; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}