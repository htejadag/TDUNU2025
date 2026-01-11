package com.MsSimulacro.MsSimulacro.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Microservicio Simulacro",
                version = "1.0",
                description = "API para gestión de Simulacros"
        )
)
public class OpenApiConfig {

}
