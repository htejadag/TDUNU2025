package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;

@Mapper(componentModel = "spring")
public interface MiembroConsejoMapper {

    @Mapping(source = "consejo.idConsejo", target = "consejo")
    MiembroConsejoResponse toResponse(MiembroConsejoModel model);

    @Mapping(target = "idMiembro", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(source = "consejo", target = "consejo", qualifiedByName = "mapIdToConsejo")
    MiembroConsejoModel toEntity(MiembroConsejoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idMiembro", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(source = "consejo", target = "consejo", qualifiedByName = "mapIdToConsejo")
    void updateEntityFromRequest(MiembroConsejoRequest request, @MappingTarget MiembroConsejoModel entity);

    @Named("mapIdToConsejo")
    default ConsejoModel mapIdToConsejo(Integer id) {
        if (id == null)
            return null;
        ConsejoModel c = new ConsejoModel();
        c.setIdConsejo(id);
        return c;
    }

}