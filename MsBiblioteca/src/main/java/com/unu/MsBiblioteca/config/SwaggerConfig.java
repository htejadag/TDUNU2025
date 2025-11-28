package com.unu.MsBiblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("API - Microservicio Biblioteca")
                                .version("1.0.0")
                                .description("Documentación del microservicio para gestión de libros usando Springdoc + OpenAPI")
                );
    }
}
