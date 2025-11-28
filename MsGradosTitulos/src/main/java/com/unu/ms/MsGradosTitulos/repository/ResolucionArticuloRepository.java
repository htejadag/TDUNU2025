package com.unu.ms.MsGradosTitulos.repository;
 
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionArticuloModel;

@Repository
public interface ResolucionArticuloRepository extends JpaRepository<ResolucionArticuloModel, Integer> {
  
}
