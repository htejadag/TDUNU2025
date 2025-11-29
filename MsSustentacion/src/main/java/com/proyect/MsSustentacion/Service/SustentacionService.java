package com.proyect.MsSustentacion.Service;

import com.proyect.MsSustentacion.model.request.SustentacionRequest;
import com.proyect.MsSustentacion.model.response.SustentacionResponse;

import java.util.List;

public interface SustentacionService {

    List<SustentacionResponse> findAll();

    SustentacionResponse findById(Long id);

    SustentacionResponse save(SustentacionRequest sustentacion);

    void delete(Long id);
}
