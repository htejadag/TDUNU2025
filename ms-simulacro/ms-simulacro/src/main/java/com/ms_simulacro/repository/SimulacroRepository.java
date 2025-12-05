package com.ms_simulacro.repository;

import com.ms_simulacro.model.Simulacro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacroRepository extends JpaRepository<Simulacro, Long> {
}
