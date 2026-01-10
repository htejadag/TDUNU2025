package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.DetalleLibroRequest;
import tdunu2025.msbiblioteca.model.response.DetalleLibroResponse;

public interface DetalleLibroService {

    List<DetalleLibroResponse> listar();

    DetalleLibroResponse obtener(Long id);

    DetalleLibroResponse registrar(DetalleLibroRequest request);

    DetalleLibroResponse actualizar (Long id, DetalleLibroRequest request);

    void eliminar(Long id);
}
