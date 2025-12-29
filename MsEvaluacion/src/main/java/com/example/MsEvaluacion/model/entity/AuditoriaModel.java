package com.example.MsEvaluacion.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

public abstract class AuditoriaModel {

    @CreatedDate
    private Instant fechaCreacion;

    @LastModifiedDate
    private Instant fechaModificacion;

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public Instant getFechaModificacion() {
        return fechaModificacion;
    }
}
