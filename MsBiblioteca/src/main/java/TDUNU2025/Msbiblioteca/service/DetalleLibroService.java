package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;

public interface DetalleLibroService {

    DetalleLibroResponse save(DetalleLibroRequest request);
    DetalleLibroResponse findById(Integer idDetalleLibro);
    List<DetalleLibroResponse> findAll();
    DetalleLibroResponse update(Integer idDetalleLibro, DetalleLibroRequest request);
    void delete(Integer idDetalleLibro);
}