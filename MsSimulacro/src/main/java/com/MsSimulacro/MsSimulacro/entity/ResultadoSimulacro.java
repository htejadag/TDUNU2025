package com.MsSimulacro.MsSimulacro.entity;

import com.MsSimulacro.MsSimulacro.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resultado_simulacro")
@Getter
@Setter
public class ResultadoSimulacro extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long estudianteId;  // ID que viene de otro microservicio
    private Double puntaje;

    @ManyToOne
    @JoinColumn(name = "sesion_id", nullable = false)
    private SesionSimulacro sesion;
}
