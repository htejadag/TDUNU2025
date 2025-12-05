package com.example.MsCursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MsCursos.model.entity.CursoModel;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel,Integer>{
    
}
