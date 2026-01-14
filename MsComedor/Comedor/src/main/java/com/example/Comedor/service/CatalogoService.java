package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.Catalogo.CatalogoRequest;
import com.example.Comedor.model.request.Catalogo.CatalogoUpdateRequest;
import com.example.Comedor.model.response.CatalogoResponse;

public interface CatalogoService {

    
    List<CatalogoResponse>listar();

    CatalogoResponse obtenerPorId(Integer id);

    CatalogoResponse guardar(CatalogoRequest catalogoRequest);
    
    CatalogoResponse modificar(Integer id,CatalogoUpdateRequest catalogoRequest);

    CatalogoResponse eliminar(Integer id);


    
}
