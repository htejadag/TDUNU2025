package com.example.MsEvaluacion.model.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "evaluacionDetalle")
public class EvaluacionDetalleModel extends AuditoriaModel {

    @Id
    private String id;

    private String evaluacion;

    private String catalogoid;

    private List<Double> notas;
    
    private Double nota;
    
    private Double promedio;

    private LocalDate fechaPrueba;

    private Integer usuarioCreacion;
    private Integer usuarioModificacion;
}
