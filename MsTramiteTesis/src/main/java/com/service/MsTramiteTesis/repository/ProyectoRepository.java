package com.service.MsTramiteTesis.repository;

import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<ProyectoTesis, Long> {

    // Buscar proyectos por estudiante
    List<ProyectoTesis> findByIdEstudianteExt(Long idEstudianteExt);

    // Buscar proyectos por asesor
    List<ProyectoTesis> findByIdAsesorExt(Long idAsesorExt);

    // Buscar proyectos por estado
    List<ProyectoTesis> findByEstadoProyectoCodigo(String estadoCodigo);

    // Buscar proyectos por asesor y estado
    List<ProyectoTesis> findByIdAsesorExtAndEstadoProyectoCodigo(Long idAsesorExt, String estadoCodigo);
}
