package com.MsSimulacro.MsSimulacro.service;

import java.util.List;
import java.util.Optional;

import com.MsSimulacro.MsSimulacro.entity.Simulacro;

public interface SimulacroService {
    List<Simulacro> listarTodos();

    Optional<Simulacro> obtenerPorId(Long id);

    Simulacro crear(Simulacro simulacro);

    Simulacro actualizar(Long id, Simulacro simulacro);

    void eliminarLogico(Long id);

}
