package com.pago.service;

import java.util.List;
import com.pago.model.request.AperturaCierreCajaRequest;
import com.pago.model.response.AperturaCierreCajaResponse;

public interface AperturaCierreCajaService {

    List<AperturaCierreCajaResponse> listar();

    AperturaCierreCajaResponse obtenerPorId(Integer id);

    AperturaCierreCajaResponse guardar(AperturaCierreCajaRequest request);

    AperturaCierreCajaResponse editar(Integer id, AperturaCierreCajaRequest request);

    void desactivar(Integer id);

    void eliminar(Integer id);
}
