package com.example.MsEvaluacion.model.events; 

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class cursoDetalleEvent {
    private Integer idDetalleCurso;
    private String cursoNombre;
}