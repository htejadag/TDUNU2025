package com.example.mscursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mscursos.model.entity.CursoDetalleModel;

@Repository
public interface CursoDetalleRepository extends JpaRepository<CursoDetalleModel, Integer> {


    boolean existsByIdCursoAndIdDocenteAndIdSemestreAndIdTipoCursoAndEstadoTrue(
            Integer idCurso,
            Integer idDocente,
            Integer idSemestre,
            Integer idTipoCurso);

}
