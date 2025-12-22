package com.example.MsPlanEstudios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MsPlanEstudios.model.entity.PlanEstudiosDetalleModel;

@Repository
public interface PlanEstudiosDetalleRepository extends JpaRepository<PlanEstudiosDetalleModel, Integer>{
    
}
