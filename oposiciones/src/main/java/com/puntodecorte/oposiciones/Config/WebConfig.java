package com.puntodecorte.oposiciones.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = System.getProperty("user.home") + "/puntodecorte/uploads/productos/";
        registry.addResourceHandler("/uploads/productos/**")
                .addResourceLocations("file:" + uploadPath);
    }
}