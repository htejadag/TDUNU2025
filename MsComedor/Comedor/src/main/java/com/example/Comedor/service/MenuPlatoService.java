package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.MenuPlatoRequest;
import com.example.Comedor.model.response.MenuPlatoResponse;

public interface MenuPlatoService {

    List<MenuPlatoResponse> listar();

    MenuPlatoResponse obtenerPorId(Integer id);

    MenuPlatoResponse guardar(MenuPlatoRequest request);

    MenuPlatoResponse modificar(Integer id, MenuPlatoRequest request);

    void eliminar(Integer id);
    
}
