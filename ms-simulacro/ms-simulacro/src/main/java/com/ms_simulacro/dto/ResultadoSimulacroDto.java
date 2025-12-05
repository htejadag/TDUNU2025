package com.ms_simulacro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoSimulacroDto {
    private Long id;
    private Long simulacroId;
    private Long postulanteId;
    private BigDecimal puntajeTotal;
}
