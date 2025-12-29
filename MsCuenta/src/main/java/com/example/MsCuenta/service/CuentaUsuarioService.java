package com.example.MsCuenta.service;

import java.util.List;

import com.example.MsCuenta.model.request.CuentaUsuario.CuentaUsuarioRequest;
import com.example.MsCuenta.model.request.CuentaUsuario.CuentaUsuarioUpdateRequest;
import com.example.MsCuenta.model.response.CuentaUsuarioResponse;

public interface CuentaUsuarioService {

    List<CuentaUsuarioResponse>listar();

    CuentaUsuarioResponse obtenerPorId(Integer id);

    CuentaUsuarioResponse guardar(CuentaUsuarioRequest cuentaUsuarioRequest);
    
    CuentaUsuarioResponse modificar(Integer id,CuentaUsuarioUpdateRequest cuentaUsuarioUpdateRequest);

    CuentaUsuarioResponse eliminar(Integer id);

    void descontarSaldo(Integer id);
 
}