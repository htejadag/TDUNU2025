package com.unu.ms.MsSecretariaAcademica.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsSecretariaAcademica.model.entity.Catalogo;
import com.unu.ms.MsSecretariaAcademica.model.request.CatalogoRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.CatalogoResponse;

@Mapper(componentModel = "spring")
public interface CatalogoMapper {

    CatalogoResponse toResponse(Catalogo model);
    
    @Mapping(target = "idCatalogo", ignore = true)
    Catalogo toEntity(CatalogoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idCatalogo", ignore = true)
    void updateEntityFromRequest(CatalogoRequest request, @MappingTarget Catalogo entity);

}