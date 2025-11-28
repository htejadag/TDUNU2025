package com.unu.ms.MsGradosTitulos.repository;
 
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsGradosTitulos.model.entity.SeguimientoModel;

@Repository
public interface SeguimientoRepository extends JpaRepository<SeguimientoModel, Integer> {
  
}
