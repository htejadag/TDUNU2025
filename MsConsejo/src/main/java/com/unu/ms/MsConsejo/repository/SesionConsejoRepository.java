package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SesionConsejoRepository extends JpaRepository<SesionConsejoModel, Integer> {
  
}
