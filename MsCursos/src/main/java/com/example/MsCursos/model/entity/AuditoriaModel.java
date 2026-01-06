package com.example.mscursos.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public  class AuditoriaModel {

    @CreatedBy
    @Column(name = "usuarioCreacion", updatable = false)
    private Integer usuarioCreacion;

    @LastModifiedBy
    @Column(name = "usuarioModificacion")
    private Integer usuarioModificacion;

    @CreatedDate
    @Column(name = "fechaCreacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedBy
    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;
}
