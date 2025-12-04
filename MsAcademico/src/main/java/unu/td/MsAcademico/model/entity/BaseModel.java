package unu.td.MsAcademico.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false)
    private Boolean estado = Boolean.TRUE;

    @Column(name = "usuarioCreacion", nullable = false, updatable = false)
    private String usuarioCreacion;

    @Column(name = "usuarioModificacion")
    private String usuarioModificacion;

    @CreatedDate
    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;
}
