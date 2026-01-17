package com.example.MsAuditoria.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .info(new Info()
            .title("Ms Auditoria")
            .version("1.0")
            .description("Documentación de la API de Ms Auditoria"));
    }
}
