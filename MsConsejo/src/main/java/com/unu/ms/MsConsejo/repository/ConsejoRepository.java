package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;  
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ConsejoRepository extends JpaRepository<ConsejoModel, Integer> {
  
}
