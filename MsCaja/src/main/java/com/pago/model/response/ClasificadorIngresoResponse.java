package com.pago.model.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ClasificadorIngresoResponse {

    // public Integer clasificadoringresoid;
    
    public String codigo;

    public String nombre;

    public Integer usuarioCreacion;

    public LocalDateTime fechaCreacion;

    public Integer usuarioModificacion;

    public LocalDateTime fechaModificacion;

    public Boolean activo;

    public Boolean esEliminado;

}
