package com.example.Comedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Comedor.model.entity.CatalogoModel;

public interface  CatalogoRepository extends JpaRepository<CatalogoModel, Integer> {
    
}
