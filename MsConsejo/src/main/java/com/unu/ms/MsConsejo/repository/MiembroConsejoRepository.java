package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiembroConsejoRepository extends JpaRepository<MiembroConsejo, Integer> {
    
    List<MiembroConsejo> findByConsejo_IdConsejo(Integer idConsejo);
    
    List<MiembroConsejo> findByIdPersona(Integer idPersona);
    
    List<MiembroConsejo> findByIdCargo(Integer idCargo);
    
    List<MiembroConsejo> findByConsejo_IdConsejoAndIdPersona(Integer idConsejo, Integer idPersona);
}
