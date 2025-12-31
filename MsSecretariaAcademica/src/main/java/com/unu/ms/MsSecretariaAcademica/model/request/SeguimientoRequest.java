package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoRequest {

    // Integer idSeguimiento;
    Integer idUsuario;
    Integer entidadCatalogoId;
    Integer entidadId;
    Integer idEstado;
    String comentario;
    // LocalDateTime fechaRegistro;

}
