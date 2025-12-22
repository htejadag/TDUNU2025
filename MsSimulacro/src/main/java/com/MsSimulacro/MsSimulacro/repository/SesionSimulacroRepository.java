package com.MsSimulacro.MsSimulacro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MsSimulacro.MsSimulacro.entity.SesionSimulacro;

public interface SesionSimulacroRepository extends JpaRepository<SesionSimulacro,Long>{
    List<SesionSimulacro> findAllByEsEliminadoFalse();

    List<SesionSimulacro> findAllBySimulacro_IdAndEsEliminadoFalse(Long simulacroId);

}
