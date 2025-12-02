package com.ms_simulacro.service;

import com.ms_simulacro.dto.ResultadoSimulacroDto;
import java.util.List;

public interface ResultadoSimulacroService {
    List<ResultadoSimulacroDto> getAll();

    ResultadoSimulacroDto getById(Long id);

    ResultadoSimulacroDto create(ResultadoSimulacroDto dto);

    ResultadoSimulacroDto update(Long id, ResultadoSimulacroDto dto);

    void delete(Long id);
}
