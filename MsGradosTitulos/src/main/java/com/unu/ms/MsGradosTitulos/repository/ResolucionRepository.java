package com.unu.ms.MsGradosTitulos.repository;

import com.unu.ms.MsGradosTitulos.model.entity.Resolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResolucionRepository extends JpaRepository<Resolucion, Integer> {
    
    Optional<Resolucion> findByNumeroResolucion(String numeroResolucion);
    
    List<Resolucion> findByIdExpediente(Integer idExpediente);
    
    List<Resolucion> findByIdSolicitud(Integer idSolicitud);
    
    List<Resolucion> findByIdEstado(Integer idEstado);
    
    List<Resolucion> findByIdTipoResolucion(Integer idTipoResolucion);
    
    List<Resolucion> findByIdExpedienteAndIdEstado(Integer idExpediente, Integer idEstado);
}
