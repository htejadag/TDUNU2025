package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.SesionConsejo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SesionConsejoRepository extends JpaRepository<SesionConsejo, Integer> {
    
    List<SesionConsejo> findByConsejo_IdConsejo(Integer idConsejo);
    
    Optional<SesionConsejo> findByNumeroSesion(String numeroSesion);
    
    List<SesionConsejo> findByFechaSesion(LocalDate fechaSesion);
    
    List<SesionConsejo> findByIdTipoSesion(Integer idTipoSesion);
    
    List<SesionConsejo> findByConsejo_IdConsejoAndFechaSesionBetween(Integer idConsejo, LocalDate fechaInicio, LocalDate fechaFin);
}
