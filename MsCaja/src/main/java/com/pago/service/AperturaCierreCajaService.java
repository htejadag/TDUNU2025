package com.pago.service;

import java.util.List;
import com.pago.model.request.AperturaCierreCajaRequest;
import com.pago.model.response.AperturaCierreCajaResponse;

public interface AperturaCierreCajaService {

    List<AperturaCierreCajaResponse> listar();

    AperturaCierreCajaResponse obtenerPorId(Integer id);

    AperturaCierreCajaResponse guardar(AperturaCierreCajaRequest request);

    AperturaCierreCajaResponse editar(Integer id, AperturaCierreCajaRequest request);

    // public abstract AperturaCierreCajaModel registrarApeCieCaja(AperturaCierreCajaModel apeCieCaja);

    // public abstract AperturaCierreCajaModel actualizarApeCieCaja(AperturaCierreCajaModel apeCieCaja);

    void desactivar(Integer id);

    void eliminar(Integer id);
}
