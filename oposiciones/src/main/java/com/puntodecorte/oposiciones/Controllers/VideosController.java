package com.puntodecorte.oposiciones.Controllers;

import com.puntodecorte.oposiciones.Dominio.Video;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VideosController {

    // Lista en memoria (temporal, para front-only)
    private final List<Video> videos = new ArrayList<>();

    @GetMapping("/videos")
    public String mostrarVideos(Model model) {
        model.addAttribute("videos", videos);
        model.addAttribute("nuevoVideo", new Video());
        return "videos";
    }

    @PostMapping("/videos")
    public String guardarVideo(@ModelAttribute Video nuevoVideo) {
        // Normalizar URL a formato embed de YouTube si es posible
        String embed = toEmbedUrl(nuevoVideo.getUrl());
        nuevoVideo.setUrl(embed);
        videos.add(nuevoVideo);
        return "redirect:/videos";
    }

    @GetMapping("/eliminar-video/{id}")
    public String eliminarVideo(@PathVariable int id) {
        if (id >= 0 && id < videos.size()) {
            videos.remove(id);
        }
        return "redirect:/videos";
    }

    private String toEmbedUrl(String url) {
        if (url == null) return "";
        try {
            URI uri = new URI(url);
            String host = uri.getHost() == null ? "" : uri.getHost().toLowerCase();
            // YouTube watch?v=...
            String query = uri.getQuery();
            if (host.contains("youtube.com") && query != null) {
                for (String part : query.split("&")) {
                    if (part.startsWith("v=")) {
                        String id = part.substring(2);
                        return "https://www.youtube.com/embed/" + id;
                    }
                }
            }
            // youtu.be short link
            if (host.contains("youtu.be")) {
                String path = uri.getPath(); // /VIDEOID
                if (path != null && path.length() > 1) {
                    return "https://www.youtube.com/embed/" + path.substring(1);
                }
            }
        } catch (URISyntaxException ignored) {}
        // Si no se pudo parsear, devolver la URL tal cual (puede fallar en iframe)
        return url;
    }
}