package com.example.MsEvaluacion.model.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class EDetalleResquest {

    private String evaluacion;
    
    private List<Double> notas;
    
    private Double nota;
    
    private LocalDate fechaPrueba;
    private String catalogoid;
}
