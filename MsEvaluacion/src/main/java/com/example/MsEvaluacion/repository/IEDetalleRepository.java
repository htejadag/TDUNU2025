package com.example.MsEvaluacion.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.MsEvaluacion.model.entity.EvaluacionDetalleModel;

public interface IEDetalleRepository extends MongoRepository<EvaluacionDetalleModel, String> {

    List<EvaluacionDetalleModel> findByEvaluacion(String evaluacion);

    boolean existsByEvaluacionAndCatalogoidAndNumero(
            String evaluacion,
            String catalogoid,
            Integer numero);

}
