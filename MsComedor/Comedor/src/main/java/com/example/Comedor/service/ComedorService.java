package com.example.Comedor.service;

import java.util.List;

import com.example.Comedor.model.request.ComedorRequest;
import com.example.Comedor.model.response.ComedorResponse;

public interface ComedorService {

    List<ComedorResponse>listar();

    ComedorResponse obtenerPorId(Integer id);

    ComedorResponse guardar(ComedorRequest comedorRequest);

    ComedorResponse modificar(Integer id,ComedorRequest comedorRequest);

    void eliminar(Integer id);








    
}
