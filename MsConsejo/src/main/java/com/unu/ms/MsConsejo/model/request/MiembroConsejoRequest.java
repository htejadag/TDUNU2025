package com.unu.ms.MsConsejo.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroConsejoRequest {

    // Integer idMiembro;
    // ConsejoModel idConsejo;
    Integer idPersona;
    Integer idCargo;
    LocalDate fechaInicio;
    // LocalDate fechaFin;
    // List<AsistenciaSesionModel> asistencias;
}
