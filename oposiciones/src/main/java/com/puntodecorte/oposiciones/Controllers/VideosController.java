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

    private final List<Video> videos = new ArrayList<>();

    /* ================= LISTAR VÍDEOS ================= */

    @GetMapping("/videos")
    public String mostrarVideos(Model model) {

        model.addAttribute("videos", videos);
        model.addAttribute("nuevoVideo", new Video());

        return "videos";
    }

    /* ================= CREAR VÍDEO ================= */

    @PostMapping("/videos")
    public String guardarVideo(@ModelAttribute Video nuevoVideo) {

        nuevoVideo.setUrl(toEmbedUrl(nuevoVideo.getUrl()));
        videos.add(nuevoVideo);

        return "redirect:/videos";
    }

    /* ================= ELIMINAR VÍDEO ================= */

    @PostMapping("/videos/eliminar/{id}")
    public String eliminarVideo(@PathVariable int id) {

        if (id >= 0 && id < videos.size()) {
            videos.remove(id);
        }

        return "redirect:/videos";
    }

    /* ================= CONVERTIR URL ================= */

    private String toEmbedUrl(String url) {

        if (url == null) return "";

        try {

            URI uri = new URI(url);

            String host = uri.getHost() == null
                    ? ""
                    : uri.getHost().toLowerCase();

            String query = uri.getQuery();

            // URL normal YouTube
            if (host.contains("youtube.com") && query != null) {

                for (String part : query.split("&")) {

                    if (part.startsWith("v=")) {

                        return "https://www.youtube.com/embed/"
                                + part.substring(2);
                    }
                }
            }

            // URL corta youtu.be
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