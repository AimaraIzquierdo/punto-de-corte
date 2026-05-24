package com.puntodecorte.oposiciones.Service;

import com.puntodecorte.oposiciones.Dominio.Video;
import com.puntodecorte.oposiciones.Repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> listarVideos() {
        return videoRepository.findAll();
    }

    public void guardarVideo(Video video) {
        videoRepository.save(video);
    }

    public void eliminarVideo(Long id) {
        videoRepository.deleteById(id);
    }
}
