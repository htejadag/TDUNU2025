package com.ms_simulacro.repository;

import com.ms_simulacro.model.SesionSimulacro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionSimulacroRepository extends JpaRepository<SesionSimulacro, Long> {
}
