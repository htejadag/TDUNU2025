package com.unu.ms.MsGradosTitulos.repository;
 
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionArticuloModel;

@Repository
public interface ResolucionArticuloRepository extends JpaRepository<ResolucionArticuloModel, Integer> {
  
    public List<ResolucionArticuloModel> findByResolucion(Integer idResolucion);
    public List<ResolucionArticuloModel> findByResolucionOrderByNumeroArticuloAsc(Integer idResolucion);

}
