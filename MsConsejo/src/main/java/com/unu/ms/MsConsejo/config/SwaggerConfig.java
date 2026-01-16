package com.unu.ms.MsConsejo.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger/OpenAPI para documentación interactiva de la API.
 * 
 * Define la información de la API y habilita la documentación automática
 * a través de Swagger UI, disponible en /swagger-ui.html. Proporciona
 * metadatos sobre el microservicio, versión y descripción general.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Configuration
public class SwaggerConfig {

  /**
   * Configura la especificación OpenAPI personalizada para la API.
   * 
   * Define la información básica del API incluyendo título, versión
   * y descripción. Esta información se muestra en la interfaz de Swagger UI.
   * 
   * @return OpenAPI configurado con información del microservicio
   */
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("API de Microservicio Consejo")
            .version("1.0")
            .description("Documentación de la API del Microservicio Consejo"));
  }

}
