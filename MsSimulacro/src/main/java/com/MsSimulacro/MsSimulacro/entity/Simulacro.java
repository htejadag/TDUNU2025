package com.MsSimulacro.MsSimulacro.entity;

import com.MsSimulacro.MsSimulacro.entity.base.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "simulacro")
@Getter
@Setter
public class Simulacro extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

}
