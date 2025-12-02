package com.example.Comedor.service;

import java.util.List;

// import com.example.Comedor.model.request.ComedorRequest;
import com.example.Comedor.model.request.MenuSemanaRequest;
// import com.example.Comedor.model.response.ComedorResponse;
import com.example.Comedor.model.response.MenuSemanaResponse;

public interface MenuSemanaService {

    List<MenuSemanaResponse>listar();

    MenuSemanaResponse obtenerPorId(Integer id);

    MenuSemanaResponse guardar(MenuSemanaRequest menuSemanaRequest);
    
    MenuSemanaResponse modificar(Integer id,MenuSemanaRequest menuSemanaRequest);

    void eliminar(Integer id);








    
}
