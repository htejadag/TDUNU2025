package com.example.MsPlanEstudios.model.entity;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "plan_estudio_prerequisito")
public class PlanEstudiosPrerequisitoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer idPlanEstudioDetalle;

    @Column(nullable = false)
    private Integer idCursoPrerequisito;

    @Column(nullable = false)
    private Boolean estado = true;

    private Integer usuarioCreacion;
    private Integer usuarioModificacion;
    private Timestamp fechaCreacion;
    private Timestamp fechaModificacion;
    
}
