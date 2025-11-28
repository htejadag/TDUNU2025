package com.unu.ms.MsGradosTitulos.model.mapper;

import org.mapstruct.*;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionModel;
import com.unu.ms.MsGradosTitulos.model.request.ResolucionRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionResponse;

@Mapper(componentModel = "spring")
public interface ResolucionMapper {

    ResolucionResponse toResponse(ResolucionModel model);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "articulos", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "expediente", ignore = true)
    ResolucionModel toEntity(ResolucionRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "articulos", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "expediente", ignore = true)
    void updateEntityFromRequest(ResolucionRequest request, @MappingTarget ResolucionModel entity);

}