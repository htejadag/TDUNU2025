package com.pago.model.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ConceptoPagoRequest {
    public Integer conceptopagoid;

    public Integer clasificadoringresoid;

    public String nombre_concepto;

    public float precio_base;

    public Integer usuario_creacion;

    public LocalDateTime fecha_creacion;

    public Integer usuario_modificacion;

    public LocalDateTime fecha_modificacion;

    public Boolean activo;

    public Boolean es_eliminado;

}
