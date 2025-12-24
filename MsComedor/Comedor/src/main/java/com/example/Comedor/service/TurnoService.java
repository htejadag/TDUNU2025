package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.turno.TurnoRequest;
import com.example.Comedor.model.request.turno.TurnoUpdateRequest;
import com.example.Comedor.model.response.TurnoResponse;

public interface TurnoService {

    List<TurnoResponse>listar();

    TurnoResponse obtenerPorId(Integer id);

    TurnoResponse guardar(TurnoRequest turnoRequest);

    TurnoResponse modificar(Integer id,TurnoUpdateRequest turnoRequest);

    TurnoResponse eliminar(Integer id);
    
}
