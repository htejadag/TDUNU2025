package com.example.MsEvaluacion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.MsEvaluacion.model.entity.EvaluacionDetalleModel;

public interface IEDetalleRepository extends MongoRepository<EvaluacionDetalleModel, String> {
    
}
