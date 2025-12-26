package com.microservice.MsCatalogoTesis.repository;

import com.microservice.MsCatalogoTesis.model.Catalogo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoRepository extends MongoRepository<Catalogo, String> {

    Optional<Catalogo> findByGrupoAndCodigo(String grupo, String codigo);

    List<Catalogo> findByGrupoOrderByOrdenAsc(String grupo);

    List<Catalogo> findByGrupoAndActivoTrueOrderByOrdenAsc(String grupo);

    boolean existsByGrupoAndCodigo(String grupo, String codigo);

    List<Catalogo> findAllByOrderByGrupoAscOrdenAsc();

}
