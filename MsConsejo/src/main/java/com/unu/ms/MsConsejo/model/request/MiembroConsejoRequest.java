package com.unu.ms.MsConsejo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroConsejoRequest {
    private Integer idMiembro;
    private Integer idConsejo;
    private Integer idPersona;
    private Integer idCargo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
