package com.example.MsEvaluacion.model.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EDetalleResponse {
    
    private String evaluacion;
    private double nota;
    private LocalDate fechaprueba;
    private String catalogoid;
    private Integer numero;
}
