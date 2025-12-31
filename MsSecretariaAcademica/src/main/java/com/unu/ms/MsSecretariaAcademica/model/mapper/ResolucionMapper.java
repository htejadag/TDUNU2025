package com.unu.ms.MsSecretariaAcademica.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsSecretariaAcademica.model.entity.ExpedienteModel;
import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionModel;
import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionResponse;

@Mapper(componentModel = "spring")
public interface ResolucionMapper {

    ResolucionResponse toResponse(ResolucionModel model);

    @Mapping(target = "idResolucion", ignore = true)
    @Mapping(target = "articulos", ignore = true)
    @Mapping(target = "expediente", source = "expediente", qualifiedByName = "mapIdToExpedienteModel")
    ResolucionModel toEntity(ResolucionRequest request);

    @Mapping(target = "idResolucion", ignore = true)
    @Mapping(target = "articulos", ignore = true)
    @Mapping(target = "expediente", source = "expediente", qualifiedByName = "mapIdToExpedienteModel")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ResolucionRequest request, @MappingTarget ResolucionModel entity);

    @Named("mapIdToExpedienteModel")
    default ExpedienteModel mapIdToExpedienteModel(Integer id) {
        if (id == null)
            return null;
        ExpedienteModel c = new ExpedienteModel();
        c.setIdEstado(id);
        return c;
    }

}