package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.MenuDiaRequest;
import com.example.Comedor.model.response.MenuDiaResponse;

public interface MenuDiaService {

    List<MenuDiaResponse>listar();

    MenuDiaResponse obtenerPorId(Integer id);

    MenuDiaResponse guardar(MenuDiaRequest menuDiaRequest);
    
    MenuDiaResponse modificar(Integer id,MenuDiaRequest menuDiaRequest);

    void eliminar(Integer id);
    
}
