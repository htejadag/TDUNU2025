package com.unu.ms.MsGradosTitulos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unu.ms.MsGradosTitulos.model.ExpedienteModel;

@Repository
public interface ExpedienteRepository extends JpaRepository<ExpedienteModel, Integer> {
    
}
