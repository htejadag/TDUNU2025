package com.unu.epg.msgestionlegal.domain.repository;

import com.unu.epg.msgestionlegal.domain.model.ExpedienteFinal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpedienteFinalRepository extends JpaRepository<ExpedienteFinal, Long> {

    // Buscar expedientes por el ID del estudiante
    List<ExpedienteFinal> findByIdEstudiante(Long idEstudiante);
}
