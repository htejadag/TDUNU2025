package com.example.MsCuenta.service;

import java.util.List;

import com.example.MsCuenta.model.request.RecargaRequest;
import com.example.MsCuenta.model.response.RecargaResponse;

public interface RecargaService {
    List<RecargaResponse>listar();

    RecargaResponse obtenerPorId(Integer id);

    RecargaResponse guardar(RecargaRequest recargaRequest);
    
    RecargaResponse modificar(Integer id,RecargaRequest recargaRequest);

    void eliminar(Integer id);
}
