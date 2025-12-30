package com.example.MsCuenta.service;

import java.util.List;


import com.example.MsCuenta.model.request.Movimiento.MovimientoUpdateRequest;
import com.example.MsCuenta.model.response.MovimientoResponse;

public interface MovimientoService {
    
    List<MovimientoResponse>listar();

    MovimientoResponse obtenerPorId(Integer id);

    MovimientoResponse guardar(Integer idCuentaUsuario,Integer monto, Integer idUsuarioCreacion);

    MovimientoResponse guardarConKafka(Integer idCuentaUsuario,Integer idUsuarioCreacion);
    
    MovimientoResponse modificar(Integer id, MovimientoUpdateRequest movimientoUpdateRequest);

    MovimientoResponse eliminar(Integer id);
}
