package com.unu.ms.MsSecretariaAcademica.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoResponse{
    
    public Integer idSeguimiento;
    public Integer idEntidadCatalogo;
    public Integer entidadId;
    public Integer idEstado;
    public String comentario;
    public Integer idUsuario;
    // public LocalDateTime fechaRegistro;
    
}
