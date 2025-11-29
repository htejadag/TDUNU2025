package com.proyect.MsSustentacion.model.response;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class SustentacionResponse {

    public String actaNumero;

    public String lugar;

    public LocalDateTime fecha;

    public LocalTime hora;

}
