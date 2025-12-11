package com.MsSimulacro.MsSimulacro.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.MsSimulacro.MsSimulacro.entity.Simulacro;
import com.MsSimulacro.MsSimulacro.repository.SimulacroRepository;
import com.MsSimulacro.MsSimulacro.service.SimulacroService;

@Service
public class SimulacroServiceImpl implements SimulacroService{
    private final SimulacroRepository simulacroRepository;

    public SimulacroServiceImpl(SimulacroRepository simulacroRepository) {
        this.simulacroRepository = simulacroRepository;
    }

    @Override
    public List<Simulacro> listarTodos() {
        return simulacroRepository.findAllByEsEliminadoFalse();
    }

    @Override
    public Optional<Simulacro> obtenerPorId(Long id) {
        return simulacroRepository.findById(id)
                .filter(s -> !Boolean.TRUE.equals(s.getEsEliminado()));
    }

    @Override
    public Simulacro crear(Simulacro simulacro) {
        simulacro.setActivo(true);
        simulacro.setEsEliminado(false);
        simulacro.setFechaCreacion(LocalDateTime.now());
        simulacro.setUsuarioCreacion("ADMI"); // luego lo puedes cambiar
        return simulacroRepository.save(simulacro);
    }

    @Override
    public Simulacro actualizar(Long id, Simulacro simulacro) {
        Simulacro existente = simulacroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Simulacro no encontrado"));

        existente.setNombre(simulacro.getNombre());
        existente.setDescripcion(simulacro.getDescripcion());
        existente.setUsuarioModificacion("SYSTEM");
        existente.setFechaModificacion(LocalDateTime.now());

        return simulacroRepository.save(existente);
    }

    @Override
    public void eliminarLogico(Long id) {
        Simulacro existente = simulacroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Simulacro no encontrado"));

        existente.setActivo(false);
        existente.setEsEliminado(true);
        existente.setUsuarioModificacion("SYSTEM");
        existente.setFechaModificacion(LocalDateTime.now());

        simulacroRepository.save(existente);
    }

}
