package com.pago.service;

import java.util.List;

import com.pago.model.request.ConceptoPagoRequest;
import com.pago.model.response.ConceptoPagoResponse;

public interface ConceptoPagoService {

    List<ConceptoPagoResponse> listar();

    ConceptoPagoResponse obtenerPorId(Integer id);

    ConceptoPagoResponse guardar(ConceptoPagoRequest request);

    ConceptoPagoResponse editar(Integer id, ConceptoPagoRequest request);

   
    void desactivar(Integer id);

    void eliminar(Integer id);
}
