package com.example.MsCuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MsCuenta.model.entity.MovimientoModel;

public interface MovimientoRepository extends JpaRepository<MovimientoModel, Integer> {
    
}
