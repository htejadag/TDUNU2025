package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaSesionResponse {

    Integer idAsistencia;
    Integer sesion;
    Integer miembro;
    Integer idEstadoAsistencia;
    Integer usuarioRegistro;
    LocalDateTime fechaRegistro;

}
