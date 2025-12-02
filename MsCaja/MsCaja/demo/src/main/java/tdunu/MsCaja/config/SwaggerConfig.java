package tdunu.MsCaja.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "MsCaja API", version = "1.0", description = "API MsCaja"))
public class SwaggerConfig {
    // Springdoc auto-configures UI and OpenAPI; keep this class to provide basic metadata.
}