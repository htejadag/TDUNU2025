package com.pago.service;

import java.util.List;
import com.pago.model.request.AperturaCierreCajaRequest;
import com.pago.model.response.AperturaCierreCajaResponse;

public interface AperturaCierreCajaService {

    List<AperturaCierreCajaResponse> listar();

    AperturaCierreCajaResponse obtenerPorId(Integer id);

<<<<<<< Updated upstream
    AperturaCierreCajaResponse guardar(AperturaCierreCajaRequest request);

    AperturaCierreCajaResponse editar(Integer id, AperturaCierreCajaRequest request);
=======
    public abstract AperturaCierreCajaModel registrarApeCieCaja(AperturaCierreCajaModel apeCieCaja);

    public abstract AperturaCierreCajaModel actualizarApeCieCaja(AperturaCierreCajaModel apeCieCaja);
>>>>>>> Stashed changes

    void desactivar(Integer id);

    void eliminar(Integer id);
}
