package com.unu.TDUNU2025.Msbiblioteca.config;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import com.unu.TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<DetalleLibro, DetalleLibroResponse> detalleLibroMap = new PropertyMap<DetalleLibro, DetalleLibroResponse>() {
            @Override
            protected void configure() {
                map().setIdLibro(source.getLibro().getIdLibro());
            }
        };
        modelMapper.addMappings(detalleLibroMap);
        return modelMapper;
    }
}