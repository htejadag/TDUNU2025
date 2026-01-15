package com.unu.ms.MsSecretariaAcademica.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsSecretariaAcademica.model.entity.ExpedienteModel;
import com.unu.ms.MsSecretariaAcademica.model.request.ExpedienteRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ExpedienteResponse;

@Mapper(componentModel = "spring")
public interface ExpedienteMapper {

    ExpedienteResponse toResponse(ExpedienteModel model);

    @Mapping(target = "idExpediente", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "resolucion", ignore = true)
    ExpedienteModel toEntity(ExpedienteRequest request);

    @Mapping(target = "idExpediente", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "resolucion", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ExpedienteRequest request, @MappingTarget ExpedienteModel entity);

}