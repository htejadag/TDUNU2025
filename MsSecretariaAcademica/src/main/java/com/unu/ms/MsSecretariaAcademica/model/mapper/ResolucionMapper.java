package com.unu.ms.MsSecretariaAcademica.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionModel;
import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionResponse;

@Mapper(componentModel = "spring")
public interface ResolucionMapper {

    ResolucionResponse toResponse(ResolucionModel model);
    
    @Mapping(target = "idResolucion", ignore = true)
    @Mapping(target = "articulos", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "expediente", ignore = true)
    @Mapping(target = "numeroResolucion", ignore = true)
    ResolucionModel toEntity(ResolucionRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idResolucion", ignore = true)
    @Mapping(target = "articulos", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "expediente", ignore = true)
    @Mapping(target = "numeroResolucion", ignore = true)
    void updateEntityFromRequest(ResolucionRequest request, @MappingTarget ResolucionModel entity);

}