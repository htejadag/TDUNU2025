package com.unu.ms.MsGradosTitulos.repository;
 
import org.springframework.stereotype.Repository;
import com.unu.ms.MsGradosTitulos.model.entity.ResolucionModel;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ResolucionRepository extends CrudRepository<ResolucionModel, Integer> {
  
}
