package com.MsSimulacro.MsSimulacro.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "MsSimulacro API",
                version = "1.0",
                description = "Microservicio de Simulacros: Simulacro, SesionSimulacro, ResultadoSimulacro",
                contact = @Contact(name = "Equipo TDUNU2025")
        )
)
public class OpenApiConfig {

}
