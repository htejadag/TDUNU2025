package com.unu.ms.MsConsejo.model.request;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaSesionRequest {
   
    // public Integer idAsistencia;
    // public SesionConsejoModel idSesion;
    // public MiembroConsejoModel idMiembro;
    public Integer idEstadoAsistencia;
    public Integer usuarioRegistro;
    public Timestamp fechaRegistro;

}
