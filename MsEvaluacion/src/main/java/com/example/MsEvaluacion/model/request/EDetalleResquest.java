package com.example.MsEvaluacion.model.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EDetalleResquest {

    private String evaluacion;
    private double nota;
    private LocalDate fechaprueba;
    private String catalogoid;
    private Integer numero;

    
}
