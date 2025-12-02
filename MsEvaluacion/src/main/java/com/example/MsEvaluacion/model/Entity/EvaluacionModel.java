package com.example.MsEvaluacion.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "evaluacion")
public class EvaluacionModel {

    @Id
    private String id;

    private double promedio;

    private String fechaEvaluacion;

    private String usuarioCreacion;

    private String usuarioModificacion;

    private String fechaCreacion;
    
    private String fechaModificacion;
    
}
