package com.unu.ms.MsSecretariaAcademica.repository;
 
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionArticuloModel;

/**
 * Repositorio JPA para la gestión de artículos de resoluciones.
 *
 * Proporciona operaciones de acceso a datos para la entidad
 * {@link ResolucionArticuloModel}, permitiendo la consulta de artículos
 * asociados a una resolución específica.
 */
@Repository
public interface ResolucionArticuloRepository extends JpaRepository<ResolucionArticuloModel, Integer> {

    /**
     * Obtiene la lista de artículos asociados a una resolución.
     *
     * @param idResolucion identificador de la resolución
     * @return lista de artículos de la resolución
     */
    public List<ResolucionArticuloModel> findByResolucion_IdResolucion(Integer idResolucion);

    /**
     * Obtiene la lista de artículos asociados a una resolución,
     * ordenados ascendentemente por número de artículo.
     *
     * @param idResolucion identificador de la resolución
     * @return lista ordenada de artículos de la resolución
     */
    public List<ResolucionArticuloModel> findByResolucion_IdResolucionOrderByNumeroArticuloAsc(Integer idResolucion);

}