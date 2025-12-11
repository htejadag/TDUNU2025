package com.unu.ms.MsConsejo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaSesionResponse {
   
    public Integer idAsistencia;
    // public SesionConsejoModel idSesion;
    // public MiembroConsejoModel idMiembro;
    public Integer idEstadoAsistencia;
    public Integer usuarioRegistro;
    // public LocalDateTime fechaRegistro;

}

