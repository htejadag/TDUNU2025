package com.ms_simulacro.service;

import com.ms_simulacro.dto.SimulacroDto;
import java.util.List;

public interface SimulacroService {
    List<SimulacroDto> getAll();

    SimulacroDto getById(Long id);

    SimulacroDto create(SimulacroDto dto);

    SimulacroDto update(Long id, SimulacroDto dto);

    void delete(Long id);
}
