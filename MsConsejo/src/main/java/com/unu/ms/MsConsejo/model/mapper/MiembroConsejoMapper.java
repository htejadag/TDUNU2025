package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;

@Mapper(componentModel = "spring", config = AuditoriaMapperConfig.class)
public interface MiembroConsejoMapper {
 
    MiembroConsejoResponse toResponse(MiembroConsejoModel model);
    
    @InheritConfiguration(name = "updateEntityFromRequest")
    @Mapping(target = "idMiembro", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(target = "consejo", ignore = true)
    @Mapping(target = "fechaFin", ignore = true)
    MiembroConsejoModel toEntity(MiembroConsejoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idMiembro", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(target = "consejo", ignore = true)
    @Mapping(target = "fechaFin", ignore = true)
    void updateEntityFromRequest(MiembroConsejoRequest request, @MappingTarget MiembroConsejoModel entity);

}