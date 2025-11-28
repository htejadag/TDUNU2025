package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.*;

import com.unu.ms.MsConsejo.model.entity.AsistenciaSesionModel;
import com.unu.ms.MsConsejo.model.request.AsistenciaSesionRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaSesionResponse;

@Mapper(componentModel = "spring")
public interface AsistenciaSesionMapper {

    AsistenciaSesionRequest toResponse(AsistenciaSesionResponse model);

    @Mapping(target = "idAsistencia", ignore = true)
    AsistenciaSesionModel toEntity(AsistenciaSesionRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idAsistencia", ignore = true)
    void updateEntityFromRequest(AsistenciaSesionRequest request, @MappingTarget AsistenciaSesionModel entity);

}
