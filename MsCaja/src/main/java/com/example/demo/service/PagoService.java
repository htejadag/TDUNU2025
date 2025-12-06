package com.example.demo.service;

import java.util.List;

import com.example.demo.model.response.PagoResponse;
import com.example.demo.model.request.PagoRequest;

public interface PagoService {

  List<PagoResponse> listar();

  PagoResponse obtenerPorId(Integer id);

  PagoResponse guardar(PagoRequest pago);
  void eliminar(Integer id);

}