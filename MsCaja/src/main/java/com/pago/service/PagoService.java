package com.pago.service;

import java.util.List;

import com.pago.model.request.PagoRequest;
import com.pago.model.response.PagoResponse;

public interface PagoService {

  List<PagoResponse> listar();

  PagoResponse obtenerPorId(Integer id);

  PagoResponse editar(Integer id, PagoRequest pago);

  PagoResponse guardar(PagoRequest pago);
  void eliminar(Integer id);

}