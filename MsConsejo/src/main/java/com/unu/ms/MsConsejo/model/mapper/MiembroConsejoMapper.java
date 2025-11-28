package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;

@Mapper(componentModel = "spring")
public interface MiembroConsejoMapper {
 
    MiembroConsejoRequest toResponse(MiembroConsejoResponse model);
    
    @Mapping(target = "idMiembro", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(target = "consejo", ignore = true)
    MiembroConsejoModel toEntity(MiembroConsejoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idMiembro", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(target = "consejo", ignore = true)
    void updateEntityFromRequest(MiembroConsejoRequest request, @MappingTarget MiembroConsejoModel entity);

}