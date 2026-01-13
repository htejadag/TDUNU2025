package com.service.MsTramiteTesis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("MS Trámite Tesis API")
                                                .version("1.0")
                                                .description("API para gestión de trámites de proyectos de tesis")
                                                .contact(new Contact()
                                                                .name("Equipo de Desarrollo")
                                                                .email("soporte@universidad.edu")))
                                .servers(List.of(
                                                new Server().url("http://localhost:82").description("Servidor Local"),
                                                new Server().url("https://api.universidad.edu")
                                                                .description("Servidor Producción")));
        }

        @Bean
        public GroupedOpenApi estudianteApi() {
                return GroupedOpenApi.builder()
                                .group("1-estudiante")
                                .displayName("Estudiante")
                                .pathsToMatch("/api/estudiante/**")
                                .build();
        }

        @Bean
        public GroupedOpenApi docenteApi() {
                return GroupedOpenApi.builder()
                                .group("2-docente")
                                .displayName("Docente (Asesor/Jurado)")
                                .pathsToMatch("/api/docente/**")
                                .build();
        }

        @Bean
        public GroupedOpenApi coordinadorApi() {
                return GroupedOpenApi.builder()
                                .group("3-coordinador")
                                .displayName("Coordinador")
                                .pathsToMatch("/api/coordinador/**")
                                .build();
        }

        @Bean
        public GroupedOpenApi allApi() {
                return GroupedOpenApi.builder()
                                .group("0-all")
                                .displayName("Todas las APIs")
                                .pathsToMatch("/api/**")
                                .build();
        }
}
