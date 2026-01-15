package com.unu.ms.MsSecretariaAcademica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unu.ms.MsSecretariaAcademica.model.entity.Catalogo;

/**
 * Repositorio JPA para la gestión de catálogos del sistema.
 *
 * Proporciona operaciones de acceso a datos para la entidad {@link Catalogo},
 * incluyendo búsquedas específicas por categoría y valor.
 */
@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {

    /**
     * Busca un catálogo por categoría y valor.
     *
     * @param categoria categoría del catálogo
     * @param valor valor asociado a la categoría
     * @return un {@link Optional} con el catálogo encontrado,
     *         o vacío si no existe
     */
    public Optional<Catalogo> findByCategoriaAndValor(String categoria, String valor);

    /**
     * Obtiene la lista de catálogos pertenecientes a una categoría.
     *
     * @param categoria categoría del catálogo
     * @return lista de catálogos asociados a la categoría
     */
    public List<Catalogo> findByCategoria(String categoria);

}
