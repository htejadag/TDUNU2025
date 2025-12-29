package com.example.MsCuenta.service;

import java.util.List;

import com.example.MsCuenta.model.request.Recarga.RecargaRequest;
import com.example.MsCuenta.model.request.Recarga.RecargaUpdateRequest;
import com.example.MsCuenta.model.response.RecargaResponse;

public interface RecargaService {
    List<RecargaResponse>listar();

    RecargaResponse obtenerPorId(Integer id);

    RecargaResponse guardar(RecargaRequest recargaRequest);
    
    RecargaResponse modificar(Integer id,RecargaUpdateRequest recargaUpdateRequest);

    RecargaResponse eliminar(Integer id);
}
