<<<<<<< HEAD
package tdunu2025.msbiblioteca.config;
=======
package TDUNU2025.Msbiblioteca.config;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

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
