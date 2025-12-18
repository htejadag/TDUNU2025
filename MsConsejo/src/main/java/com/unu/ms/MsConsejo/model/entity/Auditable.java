package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditable {

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "eliminado", nullable = false)
    private Boolean eliminado = false;

    @Column(name = "id_usuario_creo")
    private Long usuarioCreoId;

    @Column(name = "id_usuario_modifico")
    private Long usuarioModificoId;

    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}