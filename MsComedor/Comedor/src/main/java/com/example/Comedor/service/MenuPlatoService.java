package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.menuPlato.MenuPlatoRequest;
import com.example.Comedor.model.request.menuPlato.MenuPlatoUpdateRequest;
import com.example.Comedor.model.response.MenuPlatoResponse;

public interface MenuPlatoService {

    List<MenuPlatoResponse> listar();

    MenuPlatoResponse obtenerPorId(Integer id);

    MenuPlatoResponse guardar(MenuPlatoRequest request);

    MenuPlatoResponse modificar(Integer id, MenuPlatoUpdateRequest request);

    MenuPlatoResponse eliminar(Integer id);

    List<MenuPlatoResponse> listarPorMenuSemana(Integer idMenuSemana);
    
}
