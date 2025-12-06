package com.unu.ms.MsGradosTitulos.repository;

import com.unu.ms.MsGradosTitulos.model.entity.Expediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Integer> {
    
    Optional<Expediente> findByCodigoExpediente(String codigoExpediente);
    
    List<Expediente> findByIdPersona(Integer idPersona);
    
    List<Expediente> findByIdEstado(Integer idEstado);
    
    List<Expediente> findByIdPersonaAndIdEstado(Integer idPersona, Integer idEstado);
}
