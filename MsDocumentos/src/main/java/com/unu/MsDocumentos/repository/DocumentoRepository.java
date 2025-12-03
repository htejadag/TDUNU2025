package com.unu.MsDocumentos.repository;

import com.unu.MsDocumentos.model.Documento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends MongoRepository<Documento, String> {
}
