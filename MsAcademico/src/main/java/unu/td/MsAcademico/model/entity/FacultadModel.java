package unu.td.MsAcademico.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "facultad")
@EntityListeners(AuditingEntityListener.class)
public class FacultadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

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
