package com.MsSimulacro.MsSimulacro.service;

import java.util.List;
import java.util.Optional;

import com.MsSimulacro.MsSimulacro.entity.SesionSimulacro;

public interface SesionSimulacroService {
    List<SesionSimulacro> listarTodos();

    List<SesionSimulacro> listarPorSimulacro(Long simulacroId);

    Optional<SesionSimulacro> obtenerPorId(Long id);

    SesionSimulacro crear(SesionSimulacro sesion);

    SesionSimulacro actualizar(Long id, SesionSimulacro sesion);

    void eliminarLogico(Long id);

}
