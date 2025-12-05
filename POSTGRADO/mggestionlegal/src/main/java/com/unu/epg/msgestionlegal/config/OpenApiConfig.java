package com.unu.epg.msgestionlegal.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "MS Gestión Legal - API",
                version = "1.0",
                description = "API REST del microservicio de Gestión Legal (Expedientes, Certificados, Diplomas, etc.)",
                contact = @Contact(
                        name = "Manuel Chirinos",
                        email = "soporte@epg.edu.pe"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8092", description = "Servidor Local")
        }
)
public class OpenApiConfig {
}
