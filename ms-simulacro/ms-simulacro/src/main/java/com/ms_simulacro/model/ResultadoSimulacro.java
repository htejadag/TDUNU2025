package com.ms_simulacro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "resultado_simulacro")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoSimulacro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "simulacro_id")
    private Long simulacroId;

    @Column(name = "postulante_id")
    private Long postulanteId;

    @Column(name = "puntaje_total")
    private BigDecimal puntajeTotal;
}
