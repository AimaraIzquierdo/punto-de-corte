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

    public TemarioService(TemarioRepository temarioRepository) {
        this.temarioRepository = temarioRepository;
    }

    public void subirArchivo(MultipartFile file,
                             String oposicion) throws IOException {

        String nombreOriginal = file.getOriginalFilename();

        String nombreUnico = UUID.randomUUID() + "_" + nombreOriginal;

        Path rutaCarpeta = Paths.get(uploadDir);

        if (!Files.exists(rutaCarpeta)) {
            Files.createDirectories(rutaCarpeta);
        }

        Path rutaCompleta = rutaCarpeta.resolve(nombreUnico);

        Files.copy(file.getInputStream(),
                rutaCompleta,
                StandardCopyOption.REPLACE_EXISTING);

        Temario archivo = new Temario();

        archivo.setNombre(nombreOriginal);
        archivo.setRuta(rutaCompleta.toString());
        archivo.setTipo(file.getContentType());
        archivo.setOposicion(oposicion);
        archivo.setFechaSubida(LocalDateTime.now());

        temarioRepository.save(archivo);
    }

    public List<Temario> listarArchivos() {
        return temarioRepository.findAll();
    }

    public Temario obtenerArchivo(Long id) {
        return temarioRepository.findById(id).orElseThrow();
    }

    public void borrarArchivo(Long id) throws IOException {

        Temario archivo = temarioRepository.findById(id).orElseThrow();

        Files.deleteIfExists(Paths.get(archivo.getRuta()));

        temarioRepository.deleteById(id);
    }
}