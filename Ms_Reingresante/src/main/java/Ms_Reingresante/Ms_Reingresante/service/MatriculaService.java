package Ms_Reingresante.Ms_Reingresante.service;

import java.util.List;
import Ms_Reingresante.Ms_Reingresante.model.request.MatriculaRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.MatriculaResponse;

public interface MatriculaService {
    List<MatriculaResponse> listar();
    MatriculaResponse obtenerPorId(Long id);
    MatriculaResponse guardar(MatriculaRequest request);
    MatriculaResponse actualizar(Long id, MatriculaRequest request);
    void eliminar(Long id);
}