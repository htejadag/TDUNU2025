package com.unu.ms.MsSecretariaAcademica.repository;
 
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionArticuloModel;

@Repository
public interface ResolucionArticuloRepository extends JpaRepository<ResolucionArticuloModel, Integer> {
  
    public List<ResolucionArticuloModel> findByResolucion_IdResolucion(Integer idResolucion);
    public List<ResolucionArticuloModel> findByResolucion_IdResolucionOrderByNumeroArticuloAsc(Integer idResolucion);

}
