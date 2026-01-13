package com.unu.ms.MsGradosTitulos.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsGradosTitulos.model.entity.ExpedienteModel;
import com.unu.ms.MsGradosTitulos.model.request.ExpedienteRequest;
import com.unu.ms.MsGradosTitulos.model.response.ExpedienteResponse;

@Mapper(componentModel = "spring")
public interface ExpedienteMapper {

    ExpedienteResponse toResponse(ExpedienteModel model);
    
    @Mapping(target = "idExpediente", ignore = true)
    @Mapping(target = "resoluciones", ignore = true)
    ExpedienteModel toEntity(ExpedienteRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idExpediente", ignore = true)
    @Mapping(target = "resoluciones", ignore = true)
    void updateEntityFromRequest(ExpedienteRequest request, @MappingTarget ExpedienteModel entity);

}