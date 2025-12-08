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
    @Column(name = "clasificador_ingresoid")
    private int clasificador_ingresoid;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "usuario_creacion")
    private int usuario_creacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fecha_creacion;

    @Column(name = "usuario_modificacion")
    private int usuario_modificacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fecha_modificacion;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "es_eliminado")
    private boolean es_eliminado;

}
