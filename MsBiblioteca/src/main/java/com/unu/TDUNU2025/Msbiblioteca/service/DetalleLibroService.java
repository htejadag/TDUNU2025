package com.unu.TDUNU2025.Msbiblioteca.service;

import com.unu.TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;

import java.util.List;

public interface DetalleLibroService {

    DetalleLibroResponse save(DetalleLibroRequest request);
    DetalleLibroResponse findById(Integer idDetalleLibro);
    List<DetalleLibroResponse> findAll();
    DetalleLibroResponse update(Integer idDetalleLibro, DetalleLibroRequest request);
    void delete(Integer idDetalleLibro);
}