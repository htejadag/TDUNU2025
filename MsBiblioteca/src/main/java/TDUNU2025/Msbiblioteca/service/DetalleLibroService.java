package tdunu2025.msbiblioteca.service;

import tdunu2025.msbiblioteca.model.request.DetalleLibroRequest;
import tdunu2025.msbiblioteca.model.response.DetalleLibroResponse;

import java.util.List;

public interface DetalleLibroService {

    List<DetalleLibroResponse> listar();

    DetalleLibroResponse obtener(Long id);

    DetalleLibroResponse registrar(DetalleLibroRequest request);

    DetalleLibroResponse actualizar (Long id, DetalleLibroRequest request);

    void eliminar(Long id);
}
