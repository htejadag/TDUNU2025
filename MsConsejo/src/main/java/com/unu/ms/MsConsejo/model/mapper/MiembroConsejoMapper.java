package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.*;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;

@Mapper(componentModel = "spring")
public interface MiembroConsejoMapper {
 
    MiembroConsejoRequest toResponse(MiembroConsejoResponse model);
    
    @Mapping(target = "idMiembro", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    MiembroConsejoModel toEntity(MiembroConsejoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idMiembro", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    void updateEntityFromRequest(MiembroConsejoRequest request, @MappingTarget MiembroConsejoModel entity);

}