package unu.MsGestionDocumental.MsExpediente.repository;

import unu.MsGestionDocumental.MsExpediente.model.ExpedienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpedienteRepository extends JpaRepository<ExpedienteEntity, Integer> {
    
}