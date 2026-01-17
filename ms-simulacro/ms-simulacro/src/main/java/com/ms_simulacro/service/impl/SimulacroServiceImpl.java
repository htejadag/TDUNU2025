package com.ms_simulacro.service.impl;

import com.ms_simulacro.dto.SimulacroDto;
import com.ms_simulacro.model.Simulacro;
import com.ms_simulacro.repository.SimulacroRepository;
import com.ms_simulacro.service.SimulacroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimulacroServiceImpl implements SimulacroService {

    private final SimulacroRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<SimulacroDto> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, SimulacroDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SimulacroDto getById(Long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, SimulacroDto.class))
                .orElse(null);
    }

    @Override
    public SimulacroDto create(SimulacroDto dto) {
        Simulacro entity = modelMapper.map(dto, Simulacro.class);
        Simulacro saved = repository.save(entity);
        return modelMapper.map(saved, SimulacroDto.class);
    }

    @Override
    public SimulacroDto update(Long id, SimulacroDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    dto.setId(id);
                    Simulacro entity = modelMapper.map(dto, Simulacro.class);
                    Simulacro updated = repository.save(entity);
                    return modelMapper.map(updated, SimulacroDto.class);
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
