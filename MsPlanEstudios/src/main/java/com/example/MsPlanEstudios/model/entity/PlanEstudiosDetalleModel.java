package com.example.MsPlanEstudios.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "plan_estudiodetalle")
public class PlanEstudiosDetalleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "idPlanEstudio")
    private Integer idPlanEstudio;

    @Column(name = "idCurso")
    private Integer idCurso;

    @Column(name = "idCiclo")
    private Integer idCiclo;

    @Column(name = "idTipoCursoPlan")
    private Integer idTipoCursoPlan;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "usuarioCreacion")
    private Integer usuarioCreacion;

    @Column(name = "usuarioModificacion")
    private Integer usuarioModificacion;

    @Column(name = "fechaCreacion")
    private String fechaCreacion;

    @Column(name = "fechaModificacion")
    private String fechaModificacion;

    @Column(name = "creditos",nullable = false)
    private Integer creditos;

    @Column(nullable = false)
    private Integer horasTeoricas;

    @Column(nullable = false)
    private Integer horasPracticas;

    @Column(nullable = false)
    private Integer ordenEnCiclo;
}
