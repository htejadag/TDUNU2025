package com.pago.service;

import java.util.List;

import com.pago.model.request.DevolucionRequest;
import com.pago.model.response.DevolucionResponse;

public interface DevolucionService {
    List<DevolucionResponse> listar();

    DevolucionResponse obtenerPorId(Integer id);

    DevolucionResponse guardar(DevolucionRequest request);

    DevolucionResponse editar(Integer id, DevolucionRequest request);

    void eliminar(Integer id);
}
