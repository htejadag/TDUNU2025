package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.*;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;

@Mapper(componentModel = "spring")
public interface ConsejoMapper {
 
    ConsejoResponse toResponse(ConsejoModel model);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "miembros", ignore = true)
    @Mapping(target = "sesiones", ignore = true)
    ConsejoModel toEntity(ConsejoRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "miembros", ignore = true)
    @Mapping(target = "sesiones", ignore = true)
    void updateEntityFromRequest(ConsejoRequest request, @MappingTarget ConsejoModel entity);

}