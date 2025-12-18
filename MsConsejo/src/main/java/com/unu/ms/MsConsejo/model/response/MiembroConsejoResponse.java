package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroConsejoResponse {

    Integer idMiembro;
    // public ConsejoModel idConsejo;
    Integer idPersona;
    Integer idCargo;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    // List<AsistenciaSesionModel> asistencias;

}