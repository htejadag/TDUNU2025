package com.proyect.MsSustentacion.model.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class SustentacionResponse {

    public String actaNumero;

    public String lugar;

    public LocalDate fecha;

    public LocalTime hora;

}
