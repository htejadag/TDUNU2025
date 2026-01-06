package com.example.mscursos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.mscursos.model.entity.CursoModel;
import com.example.mscursos.model.request.CursoRequest;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mm = new ModelMapper();
        mm.typeMap(CursoRequest.class, CursoModel.class).addMappings(mapper -> mapper.skip(CursoModel::setId));

        return mm;
    }
}
