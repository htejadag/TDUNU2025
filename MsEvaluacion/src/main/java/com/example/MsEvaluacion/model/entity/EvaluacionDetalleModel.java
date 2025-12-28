package com.example.MsEvaluacion.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection = "evaluacionDetalle")
public class EvaluacionDetalleModel {

    @Id
    private String id;

    private String idEvaluacion;
    private BigDecimal nota;
    private LocalDateTime fechaPrueba;

    private Integer usuarioCreacion;
    private Integer usuarioModificacion;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}
