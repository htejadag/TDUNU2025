package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroConsejoResponse {

    public Integer idMiembro;
    // public ConsejoModel idConsejo;
    public Integer idPersona;
    public Integer idCargo;
    public LocalDate fechaInicio;
    public LocalDate fechaFin;
    // public List<AsistenciaSesionModel> asistencias;

}