package com.pago.model.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ConceptoPagoRequest {
    
    //public Integer conceptopagoid;

    public Integer clasificadoringresoid;

    public String nombreConcepto;

    public float precioBase;

    public Integer usuarioCreacion;

    public LocalDateTime fechaCreacion;

    public Integer usuarioModificacion;

    public LocalDateTime fechaModificacion;

    public Boolean activo;

    public Boolean esEliminado;

}
