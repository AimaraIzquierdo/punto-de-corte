package com.puntodecorte.oposiciones.Config;

import com.puntodecorte.oposiciones.Dominio.Producto;
import com.puntodecorte.oposiciones.Repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductosIniciales {

    @Bean
    CommandLineRunner cargarDatos(ProductoRepository repo) {

        return args -> {

            // EVITA DUPLICADOS
            if (repo.count() > 0) {
                return;
            }

            /* =========================
               PRODUCTO 1
               ========================= */

            Producto p1 = new Producto();

            p1.setNombreProduc(
                    "Proteína Whey Premium"
            );

            p1.setDescripProduc(
                    "Proteína ideal para opositores que entrenan y necesitan recuperación muscular."
            );

            p1.setPrecio(39.99f);

            p1.setCategoria(
                    "suplementos"
            );

            p1.setUrlProduc(
                    "https://images.unsplash.com/photo-1593095948071-474c5cc2989d?q=80&w=1200&auto=format&fit=crop"
            );

            repo.save(p1);

            /* =========================
               PRODUCTO 2
               ========================= */

            Producto p2 = new Producto();

            p2.setNombreProduc(
                    "Sudadera Opositor"
            );

            p2.setDescripProduc(
                    "Sudadera cómoda para estudiar durante largas jornadas."
            );

            p2.setPrecio(29.95f);

            p2.setCategoria(
                    "ropa"
            );

            p2.setUrlProduc(
                    "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?q=80&w=1200&auto=format&fit=crop"
            );

            repo.save(p2);

            /* =========================
               PRODUCTO 3
               ========================= */

            Producto p3 = new Producto();

            p3.setNombreProduc(
                    "Mochila de estudio"
            );

            p3.setDescripProduc(
                    "Mochila resistente con compartimentos para apuntes y portátil."
            );

            p3.setPrecio(49.90f);

            p3.setCategoria(
                    "accesorios"
            );

            p3.setUrlProduc(
                    "https://images.unsplash.com/photo-1542291026-7eec264c27ff?q=80&w=1200&auto=format&fit=crop"
            );

            repo.save(p3);

            /* =========================
               PRODUCTO 4
               ========================= */

            Producto p4 = new Producto();

            p4.setNombreProduc(
                    "Pack Subrayadores"
            );

            p4.setDescripProduc(
                    "Pack de subrayadores fluorescentes para organizar el temario."
            );

            p4.setPrecio(12.50f);

            p4.setCategoria(
                    "material"
            );

            p4.setUrlProduc(
                    "https://images.unsplash.com/photo-1517842645767-c639042777db?q=80&w=1200&auto=format&fit=crop"
            );

            repo.save(p4);

            /* =========================
               PRODUCTO 5
               ========================= */

            Producto p5 = new Producto();

            p5.setNombreProduc(
                    "Botella térmica"
            );

            p5.setDescripProduc(
                    "Botella térmica para mantener bebidas frías o calientes durante horas."
            );

            p5.setPrecio(18.75f);

            p5.setCategoria(
                    "accesorios"
            );

            p5.setUrlProduc(
                    "https://images.unsplash.com/photo-1602143407151-7111542de6e8?q=80&w=1200&auto=format&fit=crop"
            );

            repo.save(p5);

            System.out.println(
                    "DATOS MOCK INSERTADOS"
            );
        };
    }
}