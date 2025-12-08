package com.pago.service;

import java.util.List;
import com.pago.model.entity.ConceptoPagoModel;

public interface ConceptoPagoService {

    public abstract List<ConceptoPagoModel> listarConceptoPagoa();

    public abstract ConceptoPagoModel obtenerConceptoPago(int id);

    public abstract ConceptoPagoModel registrarConceptoPago(ConceptoPagoModel clasificadorPago);

    public abstract ConceptoPagoModel actualizarConceptoPago(ConceptoPagoModel clasificadorPago);

    public abstract void desactivarConceptoPago(int id);

    public abstract void eliminarConceptoPago(int id);
}
