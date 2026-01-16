package unu.MsMatriculaCepre.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS Matrícula CEPRE-UNU API")
                        .version("1.0.0")
                        .description("""
                                Microservicio de Gestión de Matrículas - Centro Preuniversitario UNU
                                
                                Este sistema gestiona:
                                • Registro y gestión de matrículas de estudiantes
                                • Administración de grupos académicos (horarios, aulas, turnos)
                                • Asignación, reasignación y desasignación de estudiantes a grupos
                                • Consultas y reportes del proceso de matrícula
                                
                                Turnos disponibles: MAÑANA y TARDE
                                """)
                        .contact(new Contact()
                                .name("")
                                .email("")
                                .url(""))
                        .license(new License()
                                .name("")
                                .url("")))
                .servers(Arrays.asList(
                        new Server()
                                .url("http://localhost:8082")
                                .description("Servidor Local - Desarrollo"),
                        new Server()
                                .url("http://localhost:8082")
                                .description("Servidor de Pruebas"),
                        new Server()
                                .url("https://api-cepre.unu.edu.pe")
                                .description("Servidor de Producción")
                ))
                .tags(Arrays.asList(
                        new Tag()
                                .name("1. Gestión de Grupos")
                                .description("Endpoints para crear, actualizar, eliminar y consultar grupos académicos"),
                        new Tag()
                                .name("2. Asignación de Estudiantes")
                                .description("Endpoints para asignar, reasignar y desasignar estudiantes a grupos"),
                        new Tag()
                                .name("3. Consultas")
                                .description("Endpoints para consultar información de grupos y asignaciones"),
                        new Tag()
                                .name("4. Matrícula")
                                .description("Endpoints para la gestión del proceso de matrícula")
                ));
    }
}