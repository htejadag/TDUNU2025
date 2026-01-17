package com.MsSimulacro.MsSimulacro.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.MsSimulacro.MsSimulacro.entity.SesionSimulacro;
import com.MsSimulacro.MsSimulacro.repository.SesionSimulacroRepository;
import com.MsSimulacro.MsSimulacro.service.SesionSimulacroService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;


@Service
public class SesionSimulacroServiceImpl implements SesionSimulacroService{
    private final SesionSimulacroRepository sesionSimulacroRepository;

    public SesionSimulacroServiceImpl(SesionSimulacroRepository sesionSimulacroRepository) {
        this.sesionSimulacroRepository = sesionSimulacroRepository;
    }

    @Override
    public List<SesionSimulacro> listarTodos() {
        return sesionSimulacroRepository.findAllByEsEliminadoFalse();
    }

    @Override
    @Cacheable(value = "sesionesPorSimulacro", key = "#simulacroId")
    public List<SesionSimulacro> listarPorSimulacro(Long simulacroId) {
        return sesionSimulacroRepository.findAllBySimulacro_IdAndEsEliminadoFalse(simulacroId);
    }

    @Override
    public Optional<SesionSimulacro> obtenerPorId(Long id) {
        return sesionSimulacroRepository.findById(id)
                .filter(s -> !Boolean.TRUE.equals(s.getEsEliminado()));
    }

    @Override
    @CacheEvict(value = "sesionesPorSimulacro", allEntries = true)
    public SesionSimulacro crear(SesionSimulacro sesion) {
        sesion.setActivo(true);
        sesion.setEsEliminado(false);
        sesion.setFechaCreacion(LocalDateTime.now());
        sesion.setUsuarioCreacion("ADMIN");
        return sesionSimulacroRepository.save(sesion);
    }

    @Override
    @CacheEvict(value = "sesionesPorSimulacro", allEntries = true)
    public SesionSimulacro actualizar(Long id, SesionSimulacro sesion) {
        SesionSimulacro existente = sesionSimulacroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión de simulacro no encontrada"));

        existente.setFechaInicio(sesion.getFechaInicio());
        existente.setFechaFin(sesion.getFechaFin());
        existente.setAula(sesion.getAula());
        existente.setDescripcion(sesion.getDescripcion());
        existente.setSimulacro(sesion.getSimulacro());
        existente.setUsuarioModificacion("ADMIN");
        existente.setFechaModificacion(LocalDateTime.now());

        return sesionSimulacroRepository.save(existente);
    }

    @Override
    @CacheEvict(value = "sesionesPorSimulacro", allEntries = true)
    public void eliminarLogico(Long id) {
        SesionSimulacro existente = sesionSimulacroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión de simulacro no encontrada"));

        existente.setActivo(false);
        existente.setEsEliminado(true);
        existente.setUsuarioModificacion("ADMIN");
        existente.setFechaModificacion(LocalDateTime.now());

        sesionSimulacroRepository.save(existente);
    }

}
