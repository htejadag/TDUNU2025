package com.MsSimulacro.MsSimulacro.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false)
    private Boolean esEliminado = false;

    private String usuarioCreacion;

    private LocalDateTime fechaCreacion;

    private String usuarioModificacion;

    private LocalDateTime fechaModificacion;
}
