package com.example.MsEvaluacion.model.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "catalogo")
public class CatalogoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String tipo;
    private String codigo; 
    private String nombre;
    private Boolean estado;
}
