package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.AsistenciaSesionModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AsistenciaSesionRepository extends JpaRepository<AsistenciaSesionModel, Integer> {
  
}
