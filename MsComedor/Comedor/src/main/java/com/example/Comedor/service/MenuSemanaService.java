package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.menuSemana.MenuSemanaRequest;
import com.example.Comedor.model.request.menuSemana.MenuSemanaUpdateRequest;
import com.example.Comedor.model.response.MenuSemanaResponse;

public interface MenuSemanaService {

    List<MenuSemanaResponse>listar();

    MenuSemanaResponse obtenerPorId(Integer id);

    MenuSemanaResponse guardar(MenuSemanaRequest menuSemanaRequest);
    
    MenuSemanaResponse modificar(Integer id,MenuSemanaUpdateRequest menuSemanaRequest);

    MenuSemanaResponse eliminar(Integer id);









    
}
