package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.ConsumoRacionRequest;
// import com.example.Comedor.model.request.MenuDiaRequest;
// import com.example.Comedor.model.response.MenuDiaResponse;
import com.example.Comedor.model.response.ConsumoRacionResponse;

public interface ConsumoRacionService {
    List<ConsumoRacionResponse>listar();

    ConsumoRacionResponse obtenerPorId(Integer id);

    ConsumoRacionResponse guardar(ConsumoRacionRequest consumoRacionRequest);
    
    ConsumoRacionResponse modificar(Integer id,ConsumoRacionRequest consumoRacionRequest);

    void eliminar(Integer id);
}
