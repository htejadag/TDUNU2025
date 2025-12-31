package TDUNU2025.Msbiblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String API_TITLE = "API Msbiblioteca - TDUNU 2025";
    private static final String API_VERSION = "1.0";
    private static final String API_DESCRIPTION = "Documentación del microservicio de biblioteca para gestión de Autores.";
    private static final String TERMS_OF_SERVICE_URL = "http://swagger.io/terms/";
    private static final String LICENSE_NAME = "Apache 2.0";
    private static final String LICENSE_URL = "http://springdoc.org";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(API_TITLE)
                        .version(API_VERSION)
                        .description(API_DESCRIPTION)
                        .termsOfService(TERMS_OF_SERVICE_URL)
                        .license(new License()
                                .name(LICENSE_NAME)
                                .url(LICENSE_URL)));
    }
}