package com.unu.ms.MsConsejo.model.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE // Los campos de auditoría heredados se ignoran automáticamente
)
public interface AuditoriaMapperConfig {

}
