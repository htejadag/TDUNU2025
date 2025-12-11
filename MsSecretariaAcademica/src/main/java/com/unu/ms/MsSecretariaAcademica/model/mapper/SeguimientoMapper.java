package com.unu.ms.MsSecretariaAcademica.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsSecretariaAcademica.model.entity.SeguimientoModel;
import com.unu.ms.MsSecretariaAcademica.model.request.SeguimientoRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.SeguimientoResponse;

@Mapper(componentModel = "spring")
public interface SeguimientoMapper {

    SeguimientoResponse toResponse(SeguimientoModel model);
    
    @Mapping(target = "idSeguimiento", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    SeguimientoModel toEntity(SeguimientoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idSeguimiento", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    void updateEntityFromRequest(SeguimientoRequest request, @MappingTarget SeguimientoModel entity);

}