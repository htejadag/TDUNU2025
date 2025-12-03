package com.unu.ms.MsGradosTitulos.service;

import java.util.List;

import com.unu.ms.MsGradosTitulos.dto.*;

public interface IExpedienteService {

    // Guardar o agregar un expediente
    ExpedienteResponse agregar(ExpedienteRequest request);

    // Actualizar expediente por id
    ExpedienteResponse actualizarExpediente(Integer id, ExpedienteRequest request);

    // Obtener por id
    ExpedienteResponse obtenerExpedientePorId(Integer id);

    // Obtener todos
    List<ExpedienteResponse> obtenerExpedientes();

    // Eliminar
    boolean eliminarExpediente(Integer id);
}
 
