package unu.td.MsAcademico.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false)
    private Boolean activo = Boolean.TRUE;

    @Column(nullable = false)
    private Boolean eliminado = Boolean.FALSE;

    @CreatedBy
    @Column(name = "usuarioCreacion", nullable = false, updatable = false)
    private String usuarioCreacion;

    @LastModifiedBy
    @Column(name = "usuarioModificacion")
    private String usuarioModificacion;

    @CreatedDate
    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;
}
