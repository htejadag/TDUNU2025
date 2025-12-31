package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;

@Mapper(componentModel = "spring")
public interface SesionConsejoMapper {

    SesionConsejoResponse toResponse(SesionConsejoModel model);

    @Mapping(target = "idSesion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(target = "consejo", source = "consejo", qualifiedByName = "mapIdToConsejo")
    SesionConsejoModel toEntity(SesionConsejoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idSesion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(target = "consejo", source = "consejo", qualifiedByName = "mapIdToConsejo")
    void updateEntityFromRequest(SesionConsejoRequest request, @MappingTarget SesionConsejoModel entity);

    @Named("mapIdToConsejo")
    default ConsejoModel mapIdToConsejo(Integer id) {
        if (id == null) return null;
        ConsejoModel c = new ConsejoModel();
        c.setIdConsejo(id);
        return c;
    }

}
