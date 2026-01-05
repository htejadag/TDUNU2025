package com.unu.ms.MsSecretariaAcademica.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsSecretariaAcademica.model.entity.Seguimiento;

/**
 * Repositorio JPA para la gestión de seguimientos.
 *
 * Proporciona operaciones de acceso a datos para la entidad {@link Seguimiento},
 * permitiendo la consulta del historial de estados y acciones
 * realizadas sobre las entidades del microservicio.
 */
@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {

    /**
     * Obtiene la lista de seguimientos asociados a una entidad específica.
     *
     * @param entidadCatalogoId identificador del catálogo de la entidad
     * @param entidadId identificador de la entidad
     * @return lista de seguimientos asociados a la entidad
     */
    public List<Seguimiento> findByEntidadCatalogoIdAndEntidadId(
            Integer entidadCatalogoId,
            Integer entidadId
    );

    /**
     * Obtiene la lista de seguimientos filtrados por estado.
     *
     * @param idEstado identificador del estado
     * @return lista de seguimientos en el estado indicado
     */
    public List<Seguimiento> findByIdEstado(Integer idEstado);

    /**
     * Obtiene la lista de seguimientos registrados por un usuario.
     *
     * @param idUsuario identificador del usuario
     * @return lista de seguimientos del usuario
     */
    public List<Seguimiento> findByIdUsuario(Integer idUsuario);

    /**
     * Obtiene la lista de seguimientos asociados a una entidad,
     * ordenados de forma descendente por fecha de registro.
     *
     * @param entidadCatalogoId identificador del catálogo de la entidad
     * @param entidadId identificador de la entidad
     * @return lista de seguimientos ordenados por fecha de registro descendente
     */
    public List<Seguimiento> findByEntidadCatalogoIdAndEntidadIdOrderByFechaRegistroDesc(
            Integer entidadCatalogoId,
            Integer entidadId
    );

}