package tdunu2025.Msbiblioteca.service;

import java.util.List;

import tdunu2025.Msbiblioteca.model.request.DetalleLibroRequest;
import tdunu2025.Msbiblioteca.model.response.DetalleLibroResponse;

public interface DetalleLibroService {

    List<DetalleLibroResponse> listar();

    DetalleLibroResponse obtener(Long id);

    DetalleLibroResponse registrar(DetalleLibroRequest request);

    DetalleLibroResponse actualizar (Long id, DetalleLibroRequest request);

    void eliminar(Long id);
}
