package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;

@Mapper(componentModel = "spring")
public interface ConsejoMapper {
 
    ConsejoResponse toResponse(ConsejoModel model);
    
    @Mapping(target = "idConsejo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "miembros", ignore = true)
    @Mapping(target = "sesiones", ignore = true)
    ConsejoModel toEntity(ConsejoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idConsejo", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "miembros", ignore = true)
    @Mapping(target = "sesiones", ignore = true)
    void updateEntityFromRequest(ConsejoRequest request, @MappingTarget ConsejoModel entity);

}