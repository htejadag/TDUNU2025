package com.MsSimulacroExamen.ms_SesionSimulacro.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Sesión de Simulacro")
                        .description("Documentación del CRUD de Sesión de Simulacro")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("TDUNU")
                                .email("soporte@unu.edu.pe")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio del Proyecto")
                        .url("https://github.com/htejadag/TDUNU2025.git")
                );
    }
}
