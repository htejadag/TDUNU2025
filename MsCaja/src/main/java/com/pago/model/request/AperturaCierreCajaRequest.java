package com.pago.model.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AperturaCierreCajaRequest {

    // public Integer aperturacierrecajaid;

    public LocalDateTime fechaApertura;

    public LocalDateTime fechaCierre;

    public Integer usuarioCreacion;

    public LocalDateTime fechaCreacion;

    public Integer usuarioModificacion;

    public LocalDateTime fechaModificacion;

    public Boolean activo;

    public Boolean esEliminado;

}
