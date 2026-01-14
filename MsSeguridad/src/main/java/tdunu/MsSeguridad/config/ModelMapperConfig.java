<<<<<<<< HEAD:MsSeguridad/src/main/java/tdunu/MsSeguridad/config/ModelMapperConfig.java
package tdunu.MsSeguridad.config;
========
package tdunu2025.msbiblioteca.config;
>>>>>>>> origin/origin/jordinTrujillo:MsBiblioteca/src/main/java/TDUNU2025/Msbiblioteca/config/ModelMapperConfig.java

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
