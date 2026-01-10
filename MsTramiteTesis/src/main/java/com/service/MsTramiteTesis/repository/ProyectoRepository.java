package com.service.MsTramiteTesis.repository;

import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<ProyectoTesis, Long> {

    List<ProyectoTesis> findByIdEstudiante(Integer idEstudiante);

    List<ProyectoTesis> findByIdAsesor(Integer idAsesor);

    List<ProyectoTesis> findByEstadoProyectoCat(Integer estadoCat);

    List<ProyectoTesis> findByIdAsesorAndEstadoProyectoCat(Integer idAsesor, Integer estadoCat);
}
