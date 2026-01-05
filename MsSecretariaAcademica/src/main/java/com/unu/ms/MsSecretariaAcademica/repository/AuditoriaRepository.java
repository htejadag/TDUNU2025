package com.unu.ms.MsSecretariaAcademica.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unu.ms.MsSecretariaAcademica.model.entity.Auditoria;

/**
 * Repositorio JPA para la gestión de registros de auditoría.
 *
 * Proporciona operaciones CRUD básicas para la entidad {@link Auditoria},
 * permitiendo el registro y consulta de acciones realizadas
 * sobre las entidades del microservicio.
 */
@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {

}
