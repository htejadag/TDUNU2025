package com.unu.ms.MsSecretariaAcademica.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsSecretariaAcademica.model.entity.Auditoria;
import com.unu.ms.MsSecretariaAcademica.model.request.AuditoriaRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.AuditoriaResponse;

@Mapper(componentModel = "spring")
public interface AuditoriaMapper {

    AuditoriaResponse toResponse(Auditoria model);
    
    @Mapping(target = "idAuditoria", ignore = true)
    Auditoria toEntity(AuditoriaRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idAuditoria", ignore = true)
    void updateEntityFromRequest(AuditoriaRequest request, @MappingTarget Auditoria entity);

}