package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.consumoRacion.ConsumoRacionRequest;
import com.example.Comedor.model.request.consumoRacion.ConsumoRacionUpdateRequest;
import com.example.Comedor.model.response.ConsumoRacionResponse;

public interface ConsumoRacionService {
    List<ConsumoRacionResponse>listar();

    ConsumoRacionResponse obtenerPorId(Integer id);

    ConsumoRacionResponse guardar(ConsumoRacionRequest consumoRacionRequest);
    
    ConsumoRacionResponse modificar(Integer id,ConsumoRacionUpdateRequest consumoRacionRequest);

    ConsumoRacionResponse eliminar(Integer id);
}
