package com.service.MsTramiteTesis.repository;

import com.service.MsTramiteTesis.model.entity.AsignacionJurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionJuradoRepository extends JpaRepository<AsignacionJurado, Long> {

    List<AsignacionJurado> findByIdProyecto(Long idProyecto);

    List<AsignacionJurado> findByIdDocente(Integer idDocente);

    List<AsignacionJurado> findByIdProyectoAndActivo(Long idProyecto, Boolean activo);

    List<AsignacionJurado> findByIdDocenteAndActivo(Integer idDocente, Boolean activo);

}
