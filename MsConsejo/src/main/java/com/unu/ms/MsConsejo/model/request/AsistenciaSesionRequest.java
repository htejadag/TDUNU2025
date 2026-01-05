package com.unu.ms.MsConsejo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaSesionRequest {

    // Integer idAsistencia;
    Integer sesion;
    Integer miembro;
    Integer idEstadoAsistencia;
    Integer usuarioRegistro;
    // LocalDateTime fechaRegistro;

}
