package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.misArchivos;
import com.puntodecorte.oposiciones.Repository.misArchivosRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class misArchivosService {
    private final misArchivosRepository archivoRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public misArchivosService(misArchivosRepository archivoRepository) {
        this.archivoRepository = archivoRepository;
    }

    public void subirArchivo(MultipartFile file) throws IOException {

        String nombreOriginal = file.getOriginalFilename();

        String nombreUnico = UUID.randomUUID() + "_" + nombreOriginal;

        Path rutaCarpeta = Paths.get(uploadDir);

        if (!Files.exists(rutaCarpeta)) {
            Files.createDirectories(rutaCarpeta);
        }

        Path rutaCompleta = rutaCarpeta.resolve(nombreUnico);

        Files.copy(file.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

        misArchivos archivo = new misArchivos();

        archivo.setNombre(nombreOriginal);
        archivo.setRuta(rutaCompleta.toString());
        archivo.setTipo(file.getContentType());
        archivo.setTamano(file.getSize());
        archivo.setFechaSubida(LocalDateTime.now());

        archivoRepository.save(archivo);
    }

    public List<misArchivos> listarArchivos() {
        return archivoRepository.findAll();
    }

    public void borrarArchivo(Long id) throws IOException {

        misArchivos archivo = archivoRepository.findById(id).orElseThrow();

        Files.deleteIfExists(Paths.get(archivo.getRuta()));

        archivoRepository.deleteById(id);
    }

    public misArchivos obtenerArchivo(Long id) {
        return archivoRepository.findById(id).orElseThrow();
    }
}
