package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Video;
import com.puntodecorte.oposiciones.Service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class VideosController {

    private final VideoService videoService;

    public VideosController(VideoService videoService) {
        this.videoService = videoService;
    }

    /* ================= LISTAR ================= */

    @GetMapping("/videos")
    public String mostrarVideos(Model model) {

        model.addAttribute("videos",
                videoService.listarVideos());

        model.addAttribute("nuevoVideo",
                new Video());

        return "videos";
    }

    /* ================= CREAR ================= */

    @PostMapping("/videos")
    public String guardarVideo(@ModelAttribute Video nuevoVideo) {

        nuevoVideo.setUrl(
                toEmbedUrl(nuevoVideo.getUrl()));

        videoService.guardarVideo(nuevoVideo);

        return "redirect:/videos";
    }

    /* ================= ELIMINAR ================= */

    @PostMapping("/videos/eliminar/{id}")
    public String eliminarVideo(@PathVariable Long id) {

        videoService.eliminarVideo(id);

        return "redirect:/videos";
    }

    /* ================= UTIL ================= */

    private String toEmbedUrl(String url) {

        if (url == null) return "";

        try {

            URI uri = new URI(url);

            String host = uri.getHost() == null
                    ? ""
                    : uri.getHost().toLowerCase();

            String query = uri.getQuery();

            // youtube normal
            if (host.contains("youtube.com")
                    && query != null) {

                for (String part : query.split("&")) {

                    if (part.startsWith("v=")) {

                        return "https://www.youtube.com/embed/"
                                + part.substring(2);
                    }
                }
            }

            // youtube corto
            if (host.contains("youtu.be")) {

                String path = uri.getPath();

                if (path != null && path.length() > 1) {

                    return "https://www.youtube.com/embed/"
                            + path.substring(1);
                }
            }

        } catch (URISyntaxException ignored) {}

        return url;
    }
}