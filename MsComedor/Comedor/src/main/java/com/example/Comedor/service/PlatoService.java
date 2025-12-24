package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.plato.PlatoRequest;
import com.example.Comedor.model.request.plato.PlatoUpdateRequest;
import com.example.Comedor.model.response.PlatoResponse;

public interface PlatoService {

    List<PlatoResponse>listar();

    PlatoResponse obtenerPorId(Integer id);

    PlatoResponse guardar(PlatoRequest platoRequest);

    PlatoResponse modificar(Integer id,PlatoUpdateRequest platoRequest);

    PlatoResponse eliminar(Integer id);
    
}
