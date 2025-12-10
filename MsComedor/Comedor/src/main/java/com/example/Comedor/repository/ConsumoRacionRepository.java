package com.example.Comedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Comedor.model.entity.ConsumoRacionModel;

@Repository
public interface ConsumoRacionRepository extends JpaRepository<ConsumoRacionModel, Integer> {
    
}
