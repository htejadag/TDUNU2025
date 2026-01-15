package com.pago.service;

import java.util.List;

import com.pago.model.request.NotaCreditoRequest;
import com.pago.model.response.NotaCreditoResponse;

public interface NotaCreditoService {
    List<NotaCreditoResponse> listar();

    NotaCreditoResponse obtenerPorId(Integer id);

    NotaCreditoResponse guardar(NotaCreditoRequest request);

    NotaCreditoResponse editar(Integer id, NotaCreditoRequest request);

    void eliminar(Integer id);
}

