package com.unu.ms.MsGradosTitulos.repository;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResolucionArticuloRepository extends JpaRepository<ResolucionArticulo, Integer> {
    
    List<ResolucionArticulo> findByIdResolucion(Integer idResolucion);
    
    List<ResolucionArticulo> findByIdResolucionOrderByNumeroArticuloAsc(Integer idResolucion);
}
