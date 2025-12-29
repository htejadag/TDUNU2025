package com.example.MsPlanEstudios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MsPlanEstudios.model.entity.PlanEstudiosModel;

@Repository
public interface PlanEstudiosRepository extends JpaRepository<PlanEstudiosModel, Integer>{
    
}
