package com.service.MsTramiteTesis.repository;

import com.service.MsTramiteTesis.model.entity.RevisionProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisionProyectoRepository extends JpaRepository<RevisionProyecto, Long> {

    List<RevisionProyecto> findByIdProyecto(Long idProyecto);

    List<RevisionProyecto> findByIdRevisorDocente(Integer idRevisorDocente);

    List<RevisionProyecto> findByIdProyectoAndIdRevisorDocente(Long idProyecto, Integer idRevisorDocente);

}
