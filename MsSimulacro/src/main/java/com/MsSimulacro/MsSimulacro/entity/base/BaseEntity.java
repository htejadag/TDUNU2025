package com.MsSimulacro.MsSimulacro.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    @PrePersist
    protected void onCreate() {
        this.activo = true;
        this.esEliminado = false;
        this.fechaCreacion = LocalDateTime.now();
        if (this.usuarioCreacion == null) {
            this.usuarioCreacion = "SYSTEM";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = LocalDateTime.now();
        if (this.usuarioModificacion == null) {
            this.usuarioModificacion = "SYSTEM";
        }
    }
}
