package com.example.mscursos.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mscursos.model.entity.CursoDetalleModel;
import com.example.mscursos.model.entity.CursoModel;
import com.example.mscursos.model.request.CursoDetalleRequest;
import com.example.mscursos.model.request.CursoRequest;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mm = new ModelMapper();

        mm.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

  
        mm.typeMap(CursoRequest.class, CursoModel.class)
                .addMappings(mapper -> mapper.skip(CursoModel::setId));

   
        mm.typeMap(CursoDetalleRequest.class, CursoDetalleModel.class)
                .addMappings(mapper -> mapper.skip(CursoDetalleModel::setId));

        return mm;
    }
}
