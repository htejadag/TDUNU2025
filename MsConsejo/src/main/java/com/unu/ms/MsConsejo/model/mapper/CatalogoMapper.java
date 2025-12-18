package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.unu.ms.MsConsejo.model.entity.CatalogoModel;
import com.unu.ms.MsConsejo.model.request.CatalogoRequest;
import com.unu.ms.MsConsejo.model.response.CatalogoResponse;

@Mapper(componentModel = "spring")
public interface CatalogoMapper {
 
    @Mapping(source = "padre.idCatalogo", target = "idPadre")
    CatalogoResponse toResponse(CatalogoModel model);
    
    @Mapping(target = "idCatalogo", ignore = true)
    @Mapping(target = "padre", ignore = true)
    @Mapping(target = "hijos", ignore = true)
    CatalogoModel toEntity(CatalogoRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idCatalogo", ignore = true)
    @Mapping(target = "padre", ignore = true)
    @Mapping(target = "hijos", ignore = true)
    void updateEntityFromRequest(CatalogoRequest request, @MappingTarget CatalogoModel entity);

}
