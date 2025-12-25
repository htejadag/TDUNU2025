package com.pago.service;

import java.util.List;

import com.pago.model.request.DetallePagoRequest;
import com.pago.model.response.DetallePagoResponse;


public interface DetallePagoService {

  List<DetallePagoResponse> listar();

  DetallePagoResponse obtenerPorId(Integer id);

  DetallePagoResponse editar(Integer id, DetallePagoRequest detallepago);

  DetallePagoResponse guardar(DetallePagoRequest detallepago);
  void eliminar(Integer id);

}