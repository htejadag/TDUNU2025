package com.MsSimulacro.MsSimulacro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MsSimulacro.MsSimulacro.entity.Simulacro;

public interface SimulacroRepository extends JpaRepository<Simulacro,Long>{
    // Solo los que no están eliminados lógicamente
    List<Simulacro> findAllByEsEliminadoFalse();
}
