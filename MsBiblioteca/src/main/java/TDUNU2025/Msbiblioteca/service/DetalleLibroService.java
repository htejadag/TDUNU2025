package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;

import java.util.List;

public interface DetalleLibroService {

    List<DetalleLibroResponse> listar();

    DetalleLibroResponse obtener(Long id);

    DetalleLibroResponse registrar(DetalleLibroRequest request);

    DetalleLibroResponse actualizar(Long id, DetalleLibroRequest request);

    void eliminar(Long id);
}
