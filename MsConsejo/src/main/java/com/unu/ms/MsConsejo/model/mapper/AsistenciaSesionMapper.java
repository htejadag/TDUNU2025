package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsConsejo.model.entity.AsistenciaSesionModel;
import com.unu.ms.MsConsejo.model.request.AsistenciaSesionRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaSesionResponse;

@Mapper(componentModel = "spring", config = AuditoriaMapperConfig.class)
public interface AsistenciaSesionMapper {

    AsistenciaSesionResponse toResponse(AsistenciaSesionModel model);

    @InheritConfiguration(name = "updateEntityFromRequest")
    @Mapping(target = "idAsistencia", ignore = true)
    @Mapping(target = "miembro", ignore = true)
    @Mapping(target = "sesion", ignore = true)
    AsistenciaSesionModel toEntity(AsistenciaSesionRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idAsistencia", ignore = true)
    @Mapping(target = "miembro", ignore = true)
    @Mapping(target = "sesion", ignore = true)
    void updateEntityFromRequest(AsistenciaSesionRequest request, @MappingTarget AsistenciaSesionModel entity);

}
