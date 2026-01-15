package com.pago.service;

import java.util.List;
import com.pago.model.request.ClasificadorIngresoRequest;
import com.pago.model.response.ClasificadorIngresoResponse;

public interface ClasificadorIngresoService {

    List<ClasificadorIngresoResponse> listar();

    ClasificadorIngresoResponse obtenerPorId(Integer id);

    ClasificadorIngresoResponse guardar(ClasificadorIngresoRequest request);

    ClasificadorIngresoResponse editar(Integer id, ClasificadorIngresoRequest request);

    void desactivar(Integer id);

    void eliminar(Integer id);
}
