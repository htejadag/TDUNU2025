package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;

@Mapper(componentModel = "spring", config = AuditoriaMapperConfig.class)
public interface SesionConsejoMapper {

    @Mapping(source = "consejo.idConsejo", target = "consejo")
    SesionConsejoResponse toResponse(SesionConsejoModel model);

    @InheritConfiguration(name = "updateEntityFromRequest")
    @Mapping(target = "idSesion", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(target = "consejo", source = "consejo", qualifiedByName = "mapIdToConsejo")
    SesionConsejoModel toEntity(SesionConsejoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idSesion", ignore = true)
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
