package com.example.MsEvaluacion.model.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EDetalleResponse implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String id;
    private String evaluacion;
    
    private List<Double> notas;
    
    private Double nota;
    
    private Double promedio;
    
    private LocalDate fechaPrueba;
    private CatalogoResponse catalogo;
}
