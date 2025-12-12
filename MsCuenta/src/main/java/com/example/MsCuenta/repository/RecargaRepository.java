package com.example.MsCuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MsCuenta.model.entity.RecargaModel;

@Repository
public interface RecargaRepository extends JpaRepository<RecargaModel, Integer> {
    
}
