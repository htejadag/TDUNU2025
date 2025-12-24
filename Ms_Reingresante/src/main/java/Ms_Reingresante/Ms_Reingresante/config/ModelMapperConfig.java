package Ms_Reingresante.Ms_Reingresante.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

@Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();

    // Evita coincidencias ambiguas como idProceso -> idMatricula
    mapper.getConfiguration()
          .setAmbiguityIgnored(true);

    return mapper;
  }
}


