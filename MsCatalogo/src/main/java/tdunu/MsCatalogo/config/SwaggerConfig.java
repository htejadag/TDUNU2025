package tdunu.MsCatalogo.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MsCatalogo API")
                        .version("1.0.0")
                        .description("Microservicio de Catálogo con Redis Cache y Kafka - Datos que no cambian frecuentemente")
                        .contact(new Contact()
                                .name("TDUNU")
                                .email("soporte@unu.edu.pe")));
    }
}
