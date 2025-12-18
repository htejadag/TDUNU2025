package com.example.MsCuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MsCuenta.model.entity.CatalogoModel;

public interface  CatalogoRepository extends JpaRepository<CatalogoModel, Integer> {
    
}
