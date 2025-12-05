package com.unu.epg.msgestionlegal.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import MsGL.MS_Gestion_Legal.domain.model.ExpedienteFinal;

 public interface ExpedientefinalRepository extends JpaRepository<ExpedienteFinal, Long> {
    List<ExpedienteFinal> findByIdEstudiante(Long idEstudiante);
}

