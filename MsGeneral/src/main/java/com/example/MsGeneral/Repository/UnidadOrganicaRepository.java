package com.example.MsGeneral.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.MsGeneral.Model.Entidad.UnidadOrganica;

@Repository
public interface UnidadOrganicaRepository extends MongoRepository<UnidadOrganica,String> {

    
} 