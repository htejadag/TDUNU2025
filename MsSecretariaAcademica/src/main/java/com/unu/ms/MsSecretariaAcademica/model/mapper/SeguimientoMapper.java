package com.unu.ms.MsSecretariaAcademica.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsSecretariaAcademica.model.entity.Seguimiento;
import com.unu.ms.MsSecretariaAcademica.model.request.SeguimientoRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.SeguimientoResponse;

@Mapper(componentModel = "spring")
public interface SeguimientoMapper {

    SeguimientoResponse toResponse(Seguimiento model);

    @Mapping(target = "idSeguimiento", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    Seguimiento toEntity(SeguimientoRequest request);

    @Mapping(target = "idSeguimiento", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(SeguimientoRequest request, @MappingTarget Seguimiento entity);

}