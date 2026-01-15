package com.pago.model.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AperturaCierreCajaRequest {

    public Integer aperturacierrecajaid;

    public LocalDateTime fecha_apertura;

    public LocalDateTime fecha_cierre;

    public Integer usuario_creacion;

    public LocalDateTime fecha_creacion;

    public Integer usuario_modificacion;

    public LocalDateTime fecha_modificacion;

    public Boolean activo;

    public Boolean es_eliminado;

}
