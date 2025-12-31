package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import TDUNU2025.Msbiblioteca.model.response.MultaResponse;

import java.util.List;

public interface MultaService {

    List<MultaResponse> listar();

    MultaResponse obtener(Integer id);

    MultaResponse registrar(MultaRequest request);

    MultaResponse actualizar(Integer id, MultaRequest request);

    void eliminar(Integer id);
    
    MultaResponse registrarPago(Integer id);
}