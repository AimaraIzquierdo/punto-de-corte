package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Modulo;
import com.puntodecorte.oposiciones.Dominio.Rol;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ModuloService {

    public List<Modulo> listarModulos() {
        List<Modulo> listaModulos = new ArrayList<>();

        Modulo modulo1 = new Modulo();
        modulo1.setNombre("Estadísticas");
        modulo1.setDescripcion("Sigue tu evolución");
        modulo1.setImagen("/img/estadisticas.png");
        modulo1.setTextoBoton("Ir a tus estadísticas");
        modulo1.setRuta("/estadisticas");
        modulo1.setRolesPermitidos(Set.of(
                Rol.OPOFREE,
                Rol.OPOPREMIUM,
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo1);

        Modulo modulo2 = new Modulo();
        modulo2.setNombre("Temario");
        modulo2.setDescripcion("¡Descarga tu temario!");
        modulo2.setImagen("/img/temario.png");
        modulo2.setTextoBoton("Ir a tu temario");
        modulo2.setRuta("/temario");
        modulo2.setRolesPermitidos(Set.of(
                Rol.OPOFREE,
                Rol.OPOPREMIUM,
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo2);

        Modulo modulo3 = new Modulo();
        modulo3.setNombre("Chat Oppi");
        modulo3.setDescripcion("Habla con Oppi y estudia con él");
        modulo3.setImagen("/img/Imagen chat ia.png");
        modulo3.setTextoBoton("Chat de Oppi");
        modulo3.setRuta("/sushito");
        modulo3.setRolesPermitidos(Set.of(
                Rol.OPOPREMIUM,
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo3);

        Modulo modulo4 = new Modulo();
        modulo4.setNombre("Tus archivos");
        modulo4.setDescripcion("Ve tus esquemas y apuntes");
        modulo4.setImagen("/img/apuntes.png");
        modulo4.setTextoBoton("Ir a tus archivos");
        modulo4.setRuta("/archivos");
        modulo4.setRolesPermitidos(Set.of(
                Rol.OPOFREE,
                Rol.OPOPREMIUM,
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo4);

        Modulo modulo5 = new Modulo();
        modulo5.setNombre("Tus flashcards");
        modulo5.setDescripcion("Crea y estudia con tus flashcards");
        modulo5.setImagen("/img/flashcards.png");
        modulo5.setTextoBoton("Ir a tus flashcards");
        modulo5.setRuta("/flashcards");
        modulo5.setRolesPermitidos(Set.of(
                Rol.OPOFREE,
                Rol.OPOPREMIUM,
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo5);

        Modulo modulo6 = new Modulo();
        modulo6.setNombre("Tus videos");
        modulo6.setDescripcion("Guarda enlaces a videos");
        modulo6.setImagen("/img/video.png");
        modulo6.setTextoBoton("Ir a tus videos");
        modulo6.setRuta("/videos");
        modulo6.setRolesPermitidos(Set.of(
                Rol.OPOFREE,
                Rol.OPOPREMIUM,
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo6);

        Modulo modulo7 = new Modulo();
        modulo7.setNombre("Tu calendario");
        modulo7.setDescripcion("¡Organízate!");
        modulo7.setImagen("/img/calendario.png");
        modulo7.setTextoBoton("Ir a tu calendario");
        modulo7.setRuta("/calendario");
        modulo7.setRolesPermitidos(Set.of(
                Rol.OPOPREMIUM,
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo7);

        Modulo modulo8 = new Modulo();
        modulo8.setNombre("Tienda");
        modulo8.setDescripcion("Compra todo lo que necesitas");
        modulo8.setImagen("/img/tienda.png");
        modulo8.setTextoBoton("Ir a la tienda");
        modulo8.setRuta("/tienda");
        modulo8.setRolesPermitidos(Set.of(
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo8);

        Modulo modulo9 = new Modulo();
        modulo9.setNombre("Tienda Opositores");
        modulo9.setDescripcion("Compra todo lo que necesitas");
        modulo9.setImagen("/img/tienda.png");
        modulo9.setTextoBoton("Ir a Tienda Opositores");
        modulo9.setRuta("/tiendaOpositor");
        modulo9.setRolesPermitidos(Set.of(
                Rol.OPOFREE,
                Rol.OPOPREMIUM,
                Rol.ADMIN
        ));
        listaModulos.add(modulo9);

        Modulo modulo10 = new Modulo();
        modulo10.setNombre("Exámenes personalizados");
        modulo10.setDescripcion("Crea exámenes por categorías y comprueba tus resultados");
        modulo10.setImagen("/img/examen_personalizado.png");
        modulo10.setTextoBoton("Ir a exámenes personalizados");
        modulo10.setRuta("/simuladorExamen");
        modulo10.setRolesPermitidos(Set.of(
                Rol.OPOFREE,
                Rol.OPOPREMIUM,
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo10);

        Modulo modulo11 = new Modulo();
        modulo11.setNombre("Exámenes oficiales");
        modulo11.setDescripcion("Haz exámenes oficiales y descubre las respuestas correctas y tu puntuación");
        modulo11.setImagen("/img/examenes.png");
        modulo11.setTextoBoton("Ir a exámenes oficiales");
        modulo11.setRuta("/examenesOficiales");
        modulo11.setRolesPermitidos(Set.of(
                Rol.OPOPREMIUM,
                Rol.OPOACADEMIA,
                Rol.ADMIN
        ));
        listaModulos.add(modulo11);

        return listaModulos;
    }
}