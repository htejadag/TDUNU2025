package com.unu.MsDocumentos.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AuditoriaEstado {

    @Field("usuarioCreacion")
    private String usuarioCreacion;

    @Field("usuarioModificacion")
    private String usuarioModificacion;

    @CreatedDate
    @Field("fechaCreacion")
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Field("fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Field("estado")
    private Boolean estado = true;
}
