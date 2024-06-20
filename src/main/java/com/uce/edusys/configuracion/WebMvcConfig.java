package com.uce.edusys.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**") // URL que se utilizará para acceder a los recursos estáticos
                .addResourceLocations("classpath:/static/"); // Ubicación física de los recursos estáticos dentro de src/main/resources/
    }
}