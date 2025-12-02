package unu.MsGestionDocumental.MsExpediente.repository;

import unu.MsGestionDocumental.MsExpediente.model.entity.ExpedienteEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpedienteRepository extends JpaRepository<ExpedienteEntity, Integer> {

    List<ExpedienteEntity> findAllByActivoTrue();
    Optional<ExpedienteEntity> findAllByAndActivoTrue(Integer id);
    
}