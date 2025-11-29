package com.proyect.MsSustentacion.model.request;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class SustentacionRequest {
    public Long id;

    public String actaNumero;

    public String lugar;

    public LocalDateTime fecha;

    public LocalTime hora;

    public Short estadoResulId;

    public Long tramiteId;
}
