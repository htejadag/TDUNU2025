package com.unu.ms.MsSecretariaAcademica.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsSecretariaAcademica.model.entity.SolicitudModel;
import com.unu.ms.MsSecretariaAcademica.model.request.SolicitudRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.SolicitudResponse;

@Mapper(componentModel = "spring")
public interface SolicitudMapper {

    SolicitudResponse toResponse(SolicitudModel model);
    
    @Mapping(target = "idSolicitud", ignore = true)
    SolicitudModel toEntity(SolicitudRequest request);

    @Mapping(target = "idSolicitud", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(SolicitudRequest request, @MappingTarget SolicitudModel entity);

}