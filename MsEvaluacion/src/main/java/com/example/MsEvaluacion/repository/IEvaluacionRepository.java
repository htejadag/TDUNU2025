package com.example.MsEvaluacion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.MsEvaluacion.model.entity.EvaluacionModel;

@Repository
public interface IEvaluacionRepository extends MongoRepository<EvaluacionModel, String> {
    long countByIdCursoDetalle(String idCursoDetalle);

    long countByIdCursoDetalleAndPromedioFinalNotNull(String idCursoDetalle);

}
