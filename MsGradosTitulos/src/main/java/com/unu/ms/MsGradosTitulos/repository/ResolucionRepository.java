package com.unu.ms.MsGradosTitulos.repository;
 
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionModel;

@Repository
public interface ResolucionRepository extends JpaRepository<ResolucionModel, Integer> {
  
    public Optional<ResolucionModel> findByNumeroResolucion(String numeroResolucion);
    public List<ResolucionModel> findByExpediente(Integer idExpediente);
    public List<ResolucionModel> findByIdSolicitud(Integer idSolicitud);
    public List<ResolucionModel> findByIdEstado(Integer idEstado);
    public List<ResolucionModel> findByIdTipoResolucion(Integer idTipoResolucion);
    public List<ResolucionModel> findByExpedienteAndIdEstado(Integer idExpediente, Integer idEstado);

}
