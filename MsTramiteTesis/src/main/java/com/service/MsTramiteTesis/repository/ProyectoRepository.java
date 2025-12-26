package com.service.MsTramiteTesis.repository;

import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<ProyectoTesis, Long> {

    List<ProyectoTesis> findByIdEstudianteExt(Long idEstudianteExt);

    List<ProyectoTesis> findByIdAsesorExt(Long idAsesorExt);

    List<ProyectoTesis> findByEstadoProyectoCodigo(String estadoCodigo);

    List<ProyectoTesis> findByIdAsesorExtAndEstadoProyectoCodigo(Long idAsesorExt, String estadoCodigo);
}
