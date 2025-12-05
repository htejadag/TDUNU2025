package com.example.MsCursos.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "curso")

public class CursoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "idCiclo")
    private Integer idCiclo;

    @Column(name = "idCarrera")
    private Integer idCarrera;

    @Column(name = "creditos")
    private Integer creditos;

    @Column(name = "horasTeoricas")
    private Integer horasTeoricas;

    @Column(name = "horasPracticas")
    private Integer horasPracticas;

    @Column(name = "estado")
    private Boolean estado;

    // Auditoría
    @Column(name = "usuarioCreacion")
    private Integer usuarioCreacion;

    @Column(name = "usuarioModificacion")
    private Integer usuarioModificacion;

    @Column(name = "fechaCreacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;
}