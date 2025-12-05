package com.unu.epg.msgestionlegal.service;

import com.unu.epg.msgestionlegal.domain.model.ExpedienteFinal;
import java.util.List;
import java.util.Optional;

public interface ExpedienteFinalService {

    // Crear nuevo expediente final
    ExpedienteFinal crear(ExpedienteFinal ex);

    // Listar todos los expedientes
    List<ExpedienteFinal> listar();

    // Buscar un expediente por ID
    Optional<ExpedienteFinal> buscarPorId(Long id);

    // Buscar expedientes por ID de estudiante
    List<ExpedienteFinal> buscarPorIdEstudiante(Long idEstudiante);

    // Actualizar el estado del expediente
    ExpedienteFinal actualizarEstado(Long id, String nuevoEstado, String observaciones);

    // Eliminar un expediente por su ID
    void eliminar(Long id);
}

