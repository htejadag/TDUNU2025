package com.example.mscursos.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
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

    @Column(name = "estado", nullable = false)
    private Boolean estado = true;

    @Embedded
    private AuditoriaModel auditoria = new AuditoriaModel();
}