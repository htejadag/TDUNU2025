package Postgrado.postgrado.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiPosgrado() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Trámite de Obtención de Grado (Posgrado)")
                        .description("Microservicio para gestionar expediente, asesor, jurado, tesis, solicitudes, documentos y revisiones.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Escuela de Posgrado")
                                .email("soporte@posgrado.unu.edu.pe")
                        )
                );
    }
}
