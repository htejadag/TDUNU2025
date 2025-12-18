package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoResponse{
    
    Integer idSeguimiento;
    Integer idUsuario;
    Integer entidadCatalogoId;
    Integer entidadId;
    Integer idEstado;
    String comentario;
    LocalDateTime fechaRegistro;
    
}
