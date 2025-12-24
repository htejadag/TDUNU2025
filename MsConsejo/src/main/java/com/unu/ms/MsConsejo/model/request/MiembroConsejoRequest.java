package com.unu.ms.MsConsejo.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroConsejoRequest {

    // private Integer idMiembro;
    // private ConsejoModel idConsejo;
    private Integer idPersona;
    private Integer idCargo;
    private LocalDate fechaInicio;
    // private LocalDate fechaFin;
    // private List<AsistenciaSesionModel> asistencias;
}
