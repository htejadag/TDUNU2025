package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.Consejo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsejoRepository extends JpaRepository<Consejo, Integer> {
    
    Optional<Consejo> findByNombre(String nombre);
    
    List<Consejo> findByIdEstado(Integer idEstado);
}
