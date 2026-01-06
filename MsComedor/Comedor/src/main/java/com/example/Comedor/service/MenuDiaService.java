package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.menuDia.MenuDiaRequest;
import com.example.Comedor.model.request.menuDia.MenuDiaUpdateRequest;
import com.example.Comedor.model.response.MenuDiaResponse;

public interface MenuDiaService {

    List<MenuDiaResponse>listar();

    MenuDiaResponse obtenerPorId(Integer id);

    MenuDiaResponse guardar(MenuDiaRequest menuDiaRequest);
    
    MenuDiaResponse modificar(Integer id,MenuDiaUpdateRequest menuDiaRequest);

    MenuDiaResponse eliminar(Integer id);
    
}
