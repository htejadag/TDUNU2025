package com.microservice.MsCatalogoTesis.repository;

import com.microservice.MsCatalogoTesis.model.Catalogo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para gestionar el catálogo en MongoDB
 */
@Repository
public interface CatalogoRepository extends MongoRepository<Catalogo, String> {

    /**
     * Buscar por grupo y código
     */
    Optional<Catalogo> findByGrupoAndCodigo(String grupo, String codigo);

    /**
     * Listar todos los elementos de un grupo
     */
    List<Catalogo> findByGrupoOrderByOrdenAsc(String grupo);

    /**
     * Listar elementos activos de un grupo
     */
    List<Catalogo> findByGrupoAndActivoTrueOrderByOrdenAsc(String grupo);

    /**
     * Verificar si existe un elemento por grupo y código
     */
    boolean existsByGrupoAndCodigo(String grupo, String codigo);

    /**
     * Listar todos ordenados por grupo y orden
     */
    List<Catalogo> findAllByOrderByGrupoAscOrdenAsc();
}
