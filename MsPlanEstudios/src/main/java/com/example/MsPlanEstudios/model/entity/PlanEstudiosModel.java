package com.example.MsPlanEstudios.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "plan_estudio")
public class PlanEstudiosModel extends AuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "idCarrera")
    private Integer idCarrera;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "año")
    private String año;

    @Column(name = "estado")
    private Boolean estado;
}
