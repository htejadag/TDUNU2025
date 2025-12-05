package MsGL.MS_Gestion_Legal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS3 - Gestión Legal / Otorgamiento de Grado")
                        .version("1.0.0")
                        .description("API oficial del Microservicio 3 encargado de gestionar los expedientes finales, certificados, diplomados, constancias y demás documentos para la obtención del grado académico."));
    }
}
