package com.ms_simulacro.service;

import com.ms_simulacro.dto.SesionSimulacroDto;
import java.util.List;

public interface SesionSimulacroService {
    List<SesionSimulacroDto> getAll();

    SesionSimulacroDto getById(Long id);

    SesionSimulacroDto create(SesionSimulacroDto dto);

    SesionSimulacroDto update(Long id, SesionSimulacroDto dto);

    void delete(Long id);
}
