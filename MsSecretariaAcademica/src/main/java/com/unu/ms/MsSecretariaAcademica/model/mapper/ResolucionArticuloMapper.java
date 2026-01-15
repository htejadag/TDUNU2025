package com.unu.ms.MsSecretariaAcademica.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionArticuloModel;
import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionArticuloResponse;

@Mapper(componentModel = "spring")
public interface ResolucionArticuloMapper {

    @Mapping(source = "resolucion.idResolucion", target = "resolucion")
    ResolucionArticuloResponse toResponse(ResolucionArticuloModel model);
    
    @Mapping(target = "idArticulo", ignore = true)
    @Mapping(target = "resolucion", ignore = true)
    ResolucionArticuloModel toEntity(ResolucionArticuloRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idArticulo", ignore = true)
    @Mapping(target = "resolucion", ignore = true)
    void updateEntityFromRequest(ResolucionArticuloRequest request, @MappingTarget ResolucionArticuloModel entity);

}