package com.pago.service;

import java.util.List;

import com.pago.model.request.ConceptoPagoRequest;
import com.pago.model.response.ConceptoPagoResponse;

public interface ConceptoPagoService {

    List<ConceptoPagoResponse> listar();

    ConceptoPagoResponse obtenerPorId(Integer id);

    ConceptoPagoResponse guardar(ConceptoPagoRequest request);

    ConceptoPagoResponse editar(Integer id, ConceptoPagoRequest request);

    // public abstract ConceptoPagoModel registrarConceptoPago(ConceptoPagoModel conceptoPago);

    // public abstract ConceptoPagoModel actualizarConceptoPago(ConceptoPagoModel conceptoPago);

    void desactivar(Integer id);

    void eliminar(Integer id);
}
