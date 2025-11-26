package unu.td.MsAcademico.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unu.td.MsAcademico.model.entity.EscuelaProfesionalModel;
import unu.td.MsAcademico.model.request.EscuelaProfesionalRequest;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

//        mapper.createTypeMap(EscuelaProfesionalRequest.class, EscuelaProfesionalModel.class)
//                .addMappings(mapping -> {
//                    mapping.skip((source, destination) ->
//                            ((EscuelaProfesionalModel) destination).setId(null)
//                    );
//                });

        return mapper;
    }
}