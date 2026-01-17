package com.example.MsAuditoria.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.MsAuditoria.Model.Entity.Auditoria;

@Repository
public interface AuditoriaRepository extends MongoRepository<Auditoria,String>{

    List<Auditoria> findByEntidadAndIdEntidad(String entidad, String idEntidad);
    List<Auditoria> findByMicroservicio(String microservicio);

}
