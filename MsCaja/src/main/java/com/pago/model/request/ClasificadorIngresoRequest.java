package com.pago.model.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ClasificadorIngresoRequest {

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
