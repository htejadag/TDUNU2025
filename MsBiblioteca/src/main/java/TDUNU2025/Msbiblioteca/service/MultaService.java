package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import TDUNU2025.Msbiblioteca.model.response.MultaResponse;

import java.util.List;

public interface MultaService {

    List<MultaResponse> listar();

    MultaResponse obtener(Long id);

    MultaResponse registrar(MultaRequest request);

    MultaResponse actualizar(Long id, MultaRequest request);

    void eliminar(Long id);
}
