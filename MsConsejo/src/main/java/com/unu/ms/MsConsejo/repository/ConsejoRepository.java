package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;  
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ConsejoRepository extends CrudRepository<ConsejoModel, Integer> {
  
}
