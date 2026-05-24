package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Temario;
import com.puntodecorte.oposiciones.Repository.TemarioRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.nio.file.*;

import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

@Service
public class TemarioService {

    private final TemarioRepository temarioRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public TemarioService(
            TemarioRepository temarioRepository) {

        this.temarioRepository =
                temarioRepository;
    }

    public void subirTemario(
            MultipartFile file,
            String oposicion)
            throws IOException {

        if (file.isEmpty()) {

            throw new RuntimeException(
                    "El archivo está vacío");
        }

        String nombreOriginal =
                file.getOriginalFilename();

        String nombreUnico =
                UUID.randomUUID()
                        + "_"
                        + nombreOriginal;

        Path carpetaUploads =
                Paths.get(uploadDir);

        if (!Files.exists(carpetaUploads)) {

            Files.createDirectories(
                    carpetaUploads);
        }

        Path rutaCompleta =
                carpetaUploads.resolve(
                        nombreUnico);

        Files.copy(
                file.getInputStream(),
                rutaCompleta,
                StandardCopyOption.REPLACE_EXISTING
        );

        Temario temario =
                new Temario();

        temario.setNombre(
                nombreOriginal);

        temario.setOposicion(
                oposicion);

        temario.setRuta(
                rutaCompleta.toAbsolutePath()
                        .toString());

        temario.setTipo(
                file.getContentType());

        temario.setTamano(
                file.getSize());

        temario.setFechaSubida(
                LocalDateTime.now());

        temarioRepository.save(
                temario);

        System.out.println(
                "PDF GUARDADO EN: "
                        + rutaCompleta.toAbsolutePath());
    }

    public List<Temario> listarPorOposicion(
            String oposicion) {

        return temarioRepository
                .findByOposicion(oposicion);
    }

    public void borrarTemario(Long id)
            throws IOException {

        Temario temario =
                temarioRepository
                        .findById(id)
                        .orElseThrow();

        Path path =
                Paths.get(
                        temario.getRuta());

        Files.deleteIfExists(path);

        temarioRepository
                .deleteById(id);
    }

    public Temario obtenerTemario(
            Long id) {

        return temarioRepository
                .findById(id)
                .orElseThrow();
    }
}
