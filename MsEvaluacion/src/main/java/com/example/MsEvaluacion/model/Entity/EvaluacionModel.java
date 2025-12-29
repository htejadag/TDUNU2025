package com.example.MsEvaluacion.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "evaluacion")
public class EvaluacionModel extends AuditoriaModel {

    @Id
    private String id;

    private double promedio;

    private String fechaEvaluacion;

    private String usuarioCreacion;

    private String usuarioModificacion;
    
}
