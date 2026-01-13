package com.MsSimulacro.MsSimulacro.service;

import java.util.List;
import java.util.Optional;

import com.MsSimulacro.MsSimulacro.entity.ResultadoSimulacro;

public interface ResultadoSimulacroService {
    List<ResultadoSimulacro> listarTodos();

    List<ResultadoSimulacro> listarPorSesion(Long sesionId);

    Optional<ResultadoSimulacro> obtenerPorId(Long id);

    ResultadoSimulacro crear(ResultadoSimulacro resultado);

    ResultadoSimulacro actualizar(Long id, ResultadoSimulacro resultado);

    void eliminarLogico(Long id);

}
