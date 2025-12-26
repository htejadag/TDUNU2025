package com.service.MsTramiteTesis.repository;

import com.service.MsTramiteTesis.model.entity.JuradoAsignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuradoRepository extends JpaRepository<JuradoAsignacion, Long> {

    List<JuradoAsignacion> findByIdProyecto(Long idProyecto);

    List<JuradoAsignacion> findByIdDocenteExtAndHabilitado(Long idDocenteExt, Boolean habilitado);
}
