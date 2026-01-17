package com.example.MsEvaluacion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.MsEvaluacion.model.entity.CursoModel;

@Repository
public interface cursoRepository extends MongoRepository<CursoModel, String> {

    CursoModel findByIdDetalleCurso(Integer idDetalleCurso);
}
