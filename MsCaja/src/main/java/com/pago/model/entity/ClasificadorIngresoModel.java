package com.pago.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clasificador_ingreso")
@Data
public class ClasificadorIngresoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clasificadoringresoid")
    private Integer clasificadorIngresoId;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "usuario_creacion")
    private Integer usuarioCreacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_modificacion", nullable = true)
    private Integer usuarioModificacion;

    @Column(name = "fecha_modificacion", nullable = true)
    private LocalDateTime fechaModificacion;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "es_eliminado")
    private Boolean esEliminado;

    @Transient
    private String mensaje;

}
