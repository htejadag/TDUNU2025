package com.unu.ms.MsConsejo.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaSesionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer idAsistencia;
    Integer sesion;
    Integer miembro;
    Integer idEstadoAsistencia;
    Integer usuarioRegistro;
    LocalDateTime fechaRegistro;

}
