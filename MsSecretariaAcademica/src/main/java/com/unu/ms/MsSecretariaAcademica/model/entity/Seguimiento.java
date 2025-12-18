package com.unu.ms.MsSecretariaAcademica.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "seguimiento", indexes = @Index(columnList = "entidadCatalogoId, entidadId"))
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private Integer idSeguimiento;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    // Catálogo: SOLICITUD / EXPEDIENTE / RESOLUCION
    @Column(name = "entidad_catalogo_id")
    private Integer entidadCatalogoId;

    @Column(name = "entidad_id")
    private Integer entidadId;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "comentario")
    private String comentario;

    @CreationTimestamp
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

}
