package com.MsSimulacroExamen.ms_SesionSimulacro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MsSimulacroExamen.ms_SesionSimulacro.entity.SesionSimulacro;
@Repository
public interface SesionSimulacroRepository extends JpaRepository<SesionSimulacro, Long>{

}
