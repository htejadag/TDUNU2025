package com.example.MsCuenta.repository;

import com.example.MsCuenta.model.entity.AuditoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<AuditoriaModel, Integer> {
}