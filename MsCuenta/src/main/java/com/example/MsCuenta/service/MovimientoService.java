package com.example.MsCuenta.service;

import java.util.List;

import com.example.MsCuenta.model.request.Movimiento.MovimientoRquest;
import com.example.MsCuenta.model.request.Movimiento.MovimientoUpdateRequest;
import com.example.MsCuenta.model.response.MovimientoResponse;

public interface MovimientoService {
    
    List<MovimientoResponse>listar();

    MovimientoResponse obtenerPorId(Integer id);

    MovimientoResponse guardar(MovimientoRquest movimientoRequest);
    
    MovimientoResponse modificar(Integer id, MovimientoUpdateRequest movimientoUpdateRequest);

    void eliminar(Integer id);
}
