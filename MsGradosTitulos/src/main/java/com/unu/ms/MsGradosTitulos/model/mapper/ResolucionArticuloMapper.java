package com.unu.ms.MsGradosTitulos.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionArticuloModel;
import com.unu.ms.MsGradosTitulos.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionArticuloResponse;

@Mapper(componentModel = "spring")
public interface ResolucionArticuloMapper {

    ResolucionArticuloResponse toResponse(ResolucionArticuloModel model);
    
    @Mapping(target = "idArticulo", ignore = true)
    ResolucionArticuloModel toEntity(ResolucionArticuloRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idArticulo", ignore = true)
    void updateEntityFromRequest(ResolucionArticuloRequest request, @MappingTarget ResolucionArticuloModel entity);

}