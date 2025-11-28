package com.ms_simulacro.repository;

import com.ms_simulacro.model.ResultadoSimulacro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoSimulacroRepository extends JpaRepository<ResultadoSimulacro, Long> {
}
