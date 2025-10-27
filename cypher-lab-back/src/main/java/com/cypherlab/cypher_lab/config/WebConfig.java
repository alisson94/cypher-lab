package com.cypherlab.cypher_lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite CORS para todos os endpoints (/**)
            .allowedOrigins("*")   // Permite requisições de qualquer origem
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
            .allowedHeaders("*");  // Headers permitidos
    }
}