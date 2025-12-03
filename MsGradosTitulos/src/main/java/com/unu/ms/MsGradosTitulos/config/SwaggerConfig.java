package com.unu.ms.MsGradosTitulos.config;

import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio de Grados y Títulos")
                        .version("1.0.0")
                        .description("API del microservicio de gestión de grados y títulos de la UNU"));
    }
}
