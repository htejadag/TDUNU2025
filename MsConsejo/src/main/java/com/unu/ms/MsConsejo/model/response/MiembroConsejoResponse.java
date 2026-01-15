package com.unu.ms.MsConsejo.model.response;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroConsejoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer idMiembro;
    Integer consejo;
    Integer idPersona;
    Integer idCargo;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    // List<AsistenciaSesionModel> asistencias;

}