package com.example.mscursos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mscursos.model.entity.CursoModel;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, Integer> {
    
    List<CursoModel> findByIdCarrera(Integer idCarrera);

    List<CursoModel> findByEstado(Boolean estado);

    List<CursoModel> findByIdCarreraAndEstado(Integer idCarrera, Boolean estado);
}
