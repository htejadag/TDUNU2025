package com.pago.model.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ClasificadorIngresoRequest {

    // public Integer clasificadoringresoid;
    
    public String codigo;

    public String nombre;

    public Integer usuario_creacion;

    public LocalDateTime usuarioCreacion;

    public Integer fechaCreacion;

    public LocalDateTime usuarioModificacion;

    public Boolean activo;

    public Boolean esEliminado;

}
