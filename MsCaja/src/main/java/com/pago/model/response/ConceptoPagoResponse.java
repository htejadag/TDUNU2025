package com.pago.model.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ConceptoPagoResponse {
    
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
