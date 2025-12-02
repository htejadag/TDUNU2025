package com.example.MsCursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MsCursos.model.entity.CursoDetalleModel;


@Repository
public interface CursoDetalleRepository extends JpaRepository<CursoDetalleModel,Integer> {

    
}
