package com.unu.ms.MsGradosTitulos.repository;
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unu.ms.MsGradosTitulos.model.entity.ExpedienteModel;

@Repository
public interface ExpedienteRepository extends JpaRepository<ExpedienteModel, Integer> {
  
    public Optional<ExpedienteModel> findByCodigoExpediente(String codigoExpediente);
    public List<ExpedienteModel> findByIdPersona(Integer idPersona);
    public List<ExpedienteModel> findByIdEstado(Integer idEstado);
    public List<ExpedienteModel> findByIdPersonaAndIdEstado(Integer idPersona, Integer idEstado);

}
