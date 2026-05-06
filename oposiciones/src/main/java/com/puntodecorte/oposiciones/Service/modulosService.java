package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.modulos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class modulosService {

    public List<modulos> listarModulos() {
        List<modulos> listaModulos = new ArrayList<>();

        modulos modulo1 = new modulos();
        modulo1.setNombre("Estadísticas");
        modulo1.setDescripcion("Sigue tu evolución");
        modulo1.setImagen("/img/estadisticas.png");
        modulo1.setTextoBoton("Ir a tus estadísticas");
        listaModulos.add(modulo1);

        modulos modulo2 = new modulos();
        modulo2.setNombre("Temario");
        modulo2.setDescripcion("¡Descarga tu temario!");
        modulo2.setImagen("/img/temario.png");
        modulo2.setTextoBoton("Ir a tu temario");
        listaModulos.add(modulo2);

        modulos modulo3 = new modulos();
        modulo3.setNombre("Chat Oppi");
        modulo3.setDescripcion("Habla con Oppi y estudia con él");
        modulo3.setImagen("/img/Imagen chat ia.png");
        modulo3.setTextoBoton("Chat de Oppi");
        listaModulos.add(modulo3);

        modulos modulo4 = new modulos();
        modulo4.setNombre("Tus archivos");
        modulo4.setDescripcion("Ve tus esquemas y apuntes");
        modulo4.setImagen("/img/apuntes.png");
        modulo4.setTextoBoton("Ir a tus archivos");
        listaModulos.add(modulo4);

        modulos modulo5 = new modulos();
        modulo5.setNombre("Tus flashcards");
        modulo5.setDescripcion("Crea y estudia con tus flashcards");
        modulo5.setImagen("/img/flashcards.png");
        modulo5.setTextoBoton("Ir a tus flashcards");
        listaModulos.add(modulo5);

        modulos modulo6 = new modulos();
        modulo6.setNombre("Tus videos");
        modulo6.setDescripcion("Guarda enlaces a videos");
        modulo6.setImagen("/img/video.png");
        modulo6.setTextoBoton("Ir a tus videos");
        listaModulos.add(modulo6);

        modulos modulo7 = new modulos();
        modulo7.setNombre("Tu calendario");
        modulo7.setDescripcion("¡Organízate!");
        modulo7.setImagen("/img/calendario.png");
        modulo7.setTextoBoton("Ir a tu calendario");
        listaModulos.add(modulo7);

        modulos modulo8 = new modulos();
        modulo8.setNombre("Tienda");
        modulo8.setDescripcion("Compra todo lo que necesitas");
        modulo8.setImagen("/img/tienda.png");
        modulo8.setTextoBoton("Ir a la tienda");
        listaModulos.add(modulo8);

        return listaModulos;
    }
}