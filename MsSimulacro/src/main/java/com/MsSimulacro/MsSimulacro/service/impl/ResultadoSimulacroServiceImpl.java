package com.MsSimulacro.MsSimulacro.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.MsSimulacro.MsSimulacro.entity.ResultadoSimulacro;
import com.MsSimulacro.MsSimulacro.repository.ResultadoSimulacroRepository;
import com.MsSimulacro.MsSimulacro.service.ResultadoSimulacroService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

@Service
public class ResultadoSimulacroServiceImpl implements ResultadoSimulacroService{

    private final ResultadoSimulacroRepository resultadoSimulacroRepository;

    public ResultadoSimulacroServiceImpl(ResultadoSimulacroRepository resultadoSimulacroRepository) {
        this.resultadoSimulacroRepository = resultadoSimulacroRepository;
    }

    @Override
    public List<ResultadoSimulacro> listarTodos() {
        return resultadoSimulacroRepository.findAllByEsEliminadoFalse();
    }

    @Override
    @Cacheable(value = "resultadosPorSesion", key = "#sesionId")
    public List<ResultadoSimulacro> listarPorSesion(Long sesionId) {
        return resultadoSimulacroRepository.findAllBySesion_IdAndEsEliminadoFalse(sesionId);
    }

    @Override
    public Optional<ResultadoSimulacro> obtenerPorId(Long id) {
        return resultadoSimulacroRepository.findById(id)
                .filter(r -> !Boolean.TRUE.equals(r.getEsEliminado()));
    }

    @Override
    @CacheEvict(value = "resultadosPorSesion", allEntries = true)
    public ResultadoSimulacro crear(ResultadoSimulacro resultado) {
        resultado.setActivo(true);
        resultado.setEsEliminado(false);
        resultado.setFechaCreacion(LocalDateTime.now());
        resultado.setUsuarioCreacion("ADMIN");
        return resultadoSimulacroRepository.save(resultado);
    }

    @Override
    @CacheEvict(value = "resultadosPorSesion", allEntries = true)
    public ResultadoSimulacro actualizar(Long id, ResultadoSimulacro resultado) {
        ResultadoSimulacro existente = resultadoSimulacroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));

        existente.setEstudianteId(resultado.getEstudianteId());
        existente.setPuntaje(resultado.getPuntaje());
        existente.setSesion(resultado.getSesion());
        existente.setUsuarioModificacion("ADMIN");
        existente.setFechaModificacion(LocalDateTime.now());

        return resultadoSimulacroRepository.save(existente);
    }

    @Override
    @CacheEvict(value = "resultadosPorSesion", allEntries = true)
    public void eliminarLogico(Long id) {
        ResultadoSimulacro existente = resultadoSimulacroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));

        existente.setActivo(false);
        existente.setEsEliminado(true);
        existente.setUsuarioModificacion("ADMIN");
        existente.setFechaModificacion(LocalDateTime.now());

        resultadoSimulacroRepository.save(existente);
    }
}
