package tdunu2025.Msbiblioteca.service;

import java.util.List;

import tdunu2025.Msbiblioteca.model.request.MultaRequest;
import tdunu2025.Msbiblioteca.model.response.MultaResponse;

public interface MultaService {

    List<MultaResponse> listar();

    MultaResponse obtener(Long id);

    MultaResponse registrar(MultaRequest request);

    MultaResponse actualizar(Long id, MultaRequest request);

    void eliminar(Long id);
}
