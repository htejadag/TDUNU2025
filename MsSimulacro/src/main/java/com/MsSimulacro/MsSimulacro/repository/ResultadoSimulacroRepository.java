package com.MsSimulacro.MsSimulacro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MsSimulacro.MsSimulacro.entity.ResultadoSimulacro;

public interface ResultadoSimulacroRepository extends JpaRepository<ResultadoSimulacro,Long>{
    List<ResultadoSimulacro> findAllByEsEliminadoFalse();

    List<ResultadoSimulacro> findAllBySesion_IdAndEsEliminadoFalse(Long sesionId);

}
