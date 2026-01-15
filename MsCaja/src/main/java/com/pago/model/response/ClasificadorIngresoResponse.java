package com.pago.model.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ClasificadorIngresoResponse {

    public Integer clasificadoringresoid;

    public String codigo;

    public String nombre;

    public Integer usuario_creacion;

    public LocalDateTime fecha_creacion;

    public Integer usuario_modificacion;

    public LocalDateTime fecha_modificacion;

    public Boolean activo;

    public Boolean es_eliminado;

}
