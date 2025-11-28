package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.*;

import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;

@Mapper(componentModel = "spring")
public interface SesionConsejoMapper {

    SesionConsejoRequest toResponse(SesionConsejoResponse model);

    @Mapping(target = "idSesion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    SesionConsejoModel toEntity(SesionConsejoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idSesion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    void updateEntityFromRequest(SesionConsejoRequest request, @MappingTarget SesionConsejoModel entity);

}
