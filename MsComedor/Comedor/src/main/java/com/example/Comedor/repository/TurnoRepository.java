package com.example.Comedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Comedor.model.entity.TurnoModel;

@Repository
public interface TurnoRepository extends JpaRepository<TurnoModel, Integer> {
    
}
