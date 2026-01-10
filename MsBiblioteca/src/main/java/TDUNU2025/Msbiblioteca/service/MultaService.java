package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.MultaRequest;
import tdunu2025.msbiblioteca.model.response.MultaResponse;

public interface MultaService {

    List<MultaResponse> listar();

    MultaResponse obtener(Long id);

    MultaResponse registrar(MultaRequest request);

    MultaResponse actualizar(Long id, MultaRequest request);

    void eliminar(Long id);
}
