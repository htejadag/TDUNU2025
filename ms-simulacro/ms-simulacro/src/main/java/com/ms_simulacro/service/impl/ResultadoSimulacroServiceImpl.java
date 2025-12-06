package com.ms_simulacro.service.impl;

import com.ms_simulacro.dto.ResultadoSimulacroDto;
import com.ms_simulacro.model.ResultadoSimulacro;
import com.ms_simulacro.repository.ResultadoSimulacroRepository;
import com.ms_simulacro.service.ResultadoSimulacroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultadoSimulacroServiceImpl implements ResultadoSimulacroService {

    private final ResultadoSimulacroRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<ResultadoSimulacroDto> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, ResultadoSimulacroDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResultadoSimulacroDto getById(Long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, ResultadoSimulacroDto.class))
                .orElse(null);
    }

    @Override
    public ResultadoSimulacroDto create(ResultadoSimulacroDto dto) {
        ResultadoSimulacro entity = modelMapper.map(dto, ResultadoSimulacro.class);
        ResultadoSimulacro saved = repository.save(entity);
        return modelMapper.map(saved, ResultadoSimulacroDto.class);
    }

    @Override
    public ResultadoSimulacroDto update(Long id, ResultadoSimulacroDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    dto.setId(id);
                    ResultadoSimulacro entity = modelMapper.map(dto, ResultadoSimulacro.class);
                    ResultadoSimulacro updated = repository.save(entity);
                    return modelMapper.map(updated, ResultadoSimulacroDto.class);
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
