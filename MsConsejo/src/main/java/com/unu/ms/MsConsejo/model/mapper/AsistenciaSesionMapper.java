package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsConsejo.model.entity.AsistenciaSesionModel;
import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.model.request.AsistenciaSesionRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaSesionResponse;

@Mapper(componentModel = "spring")
public interface AsistenciaSesionMapper {

    @Mapping(source = "sesion.idSesion", target = "sesion")
    @Mapping(source = "miembro.idMiembro", target = "miembro")
    AsistenciaSesionResponse toResponse(AsistenciaSesionModel model);

    @Mapping(target = "idAsistencia", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "sesion", source = "sesion", qualifiedByName = "mapIdToSesionConsejoModel")
    @Mapping(target = "miembro", source = "miembro", qualifiedByName = "mapIdToMiembroConsejoModel")
    AsistenciaSesionModel toEntity(AsistenciaSesionRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idAsistencia", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "sesion", source = "sesion", qualifiedByName = "mapIdToSesionConsejoModel")
    @Mapping(target = "miembro", source = "miembro", qualifiedByName = "mapIdToMiembroConsejoModel")
    void updateEntityFromRequest(AsistenciaSesionRequest request, @MappingTarget AsistenciaSesionModel entity);

    @Named("mapIdToSesionConsejoModel")
    default SesionConsejoModel mapIdToSesionConsejoModel(Integer id) {
        if (id == null)
            return null;
        SesionConsejoModel c = new SesionConsejoModel();
        c.setIdSesion(id);
        return c;
    }

    @Named("mapIdToMiembroConsejoModel")
    default MiembroConsejoModel mapIdToMiembroConsejoModel(Integer id) {
        if (id == null)
            return null;
        MiembroConsejoModel c = new MiembroConsejoModel();
        c.setIdMiembro(id);
        return c;
    }

}
