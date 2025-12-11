package com.unu.ms.MsSecretariaAcademica.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsSecretariaAcademica.model.entity.SolicitudModel;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudModel, Integer> {

   
}
