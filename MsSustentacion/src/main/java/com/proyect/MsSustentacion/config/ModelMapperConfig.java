package com.proyect.MsSustentacion.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    // SOLUCIÓN AL ERROR:
    // Configurar estrategia ESTRICTA.
    // Esto obliga a que el campo origen se llame EXACTAMENTE igual al destino.
    // Así 'fecha' solo mapeará con 'fecha', y no con 'fechaCreacion'.
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    return modelMapper;
  }
}