<<<<<<<< HEAD:MsCursos/src/main/java/com/example/MsCursos/config/SwaggerConfig.java
package com.example.MsCursos.config;
========
package TDUNU2025.Msbiblioteca.config;
>>>>>>>> origin/origin/jordinTrujillo:MsBiblioteca/src/main/java/TDUNU2025/Msbiblioteca/config/SwaggerConfig.java

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS-CURSOS API")
                        .version("1.0")
                        .description("Documentación del microservicio de Cursos"));
    }
}
