package com.example.MsEvaluacion.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "cursoEvent") 
public class CursoModel {

    @Id
    private String id;

    // Los datos que vienen de Kafka
    private Integer idDetalleCurso;
    private String cursoNombre;
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}