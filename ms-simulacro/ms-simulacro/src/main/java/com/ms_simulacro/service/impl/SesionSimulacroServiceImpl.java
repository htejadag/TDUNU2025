package com.ms_simulacro.service.impl;

import com.ms_simulacro.dto.SesionSimulacroDto;
import com.ms_simulacro.model.SesionSimulacro;
import com.ms_simulacro.repository.SesionSimulacroRepository;
import com.ms_simulacro.service.SesionSimulacroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SesionSimulacroServiceImpl implements SesionSimulacroService {

    private final SesionSimulacroRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<SesionSimulacroDto> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, SesionSimulacroDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SesionSimulacroDto getById(Long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, SesionSimulacroDto.class))
                .orElse(null);
    }

    @Override
    public SesionSimulacroDto create(SesionSimulacroDto dto) {
        SesionSimulacro entity = modelMapper.map(dto, SesionSimulacro.class);
        SesionSimulacro saved = repository.save(entity);
        return modelMapper.map(saved, SesionSimulacroDto.class);
    }

    @Override
    public SesionSimulacroDto update(Long id, SesionSimulacroDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    dto.setId(id);
                    SesionSimulacro entity = modelMapper.map(dto, SesionSimulacro.class);
                    SesionSimulacro updated = repository.save(entity);
                    return modelMapper.map(updated, SesionSimulacroDto.class);
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
