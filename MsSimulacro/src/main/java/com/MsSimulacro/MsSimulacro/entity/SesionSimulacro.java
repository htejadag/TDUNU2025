package com.MsSimulacro.MsSimulacro.entity;

import com.MsSimulacro.MsSimulacro.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sesion_simulacro")
@Getter
@Setter
public class SesionSimulacro extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    // Relación con Simulacro
    @ManyToOne
    @JoinColumn(name = "simulacro_id")
    private Simulacro simulacro;
}
