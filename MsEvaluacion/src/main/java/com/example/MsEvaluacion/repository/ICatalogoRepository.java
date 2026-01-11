
package com.example.MsEvaluacion.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.MsEvaluacion.model.entity.CatalogoModel;

public interface ICatalogoRepository  extends MongoRepository<CatalogoModel, String>{

    Optional<CatalogoModel> findByCodigo(String codigo);
}
