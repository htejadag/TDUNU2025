package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MiembroConsejoRespository extends JpaRepository<MiembroConsejoModel, Integer> {
  
}
