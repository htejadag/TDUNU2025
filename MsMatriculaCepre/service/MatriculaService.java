package unu.MsMatriculaCepre.service;

import unu.MsMatriculaCepre.model.request.MatriculaRequest;
import unu.MsMatriculaCepre.model.response.MatriculaResponse;

import java.util.List;

public interface MatriculaService {

    List<MatriculaResponse> listar();

    MatriculaResponse obtenerPorId(Integer id);

    List<MatriculaResponse> listarPorEstudiante(Integer estudianteId);

    List<MatriculaResponse> listarPorGrupo(Integer grupoId);

    MatriculaResponse guardar(MatriculaRequest request);

    MatriculaResponse actualizar(Integer id, MatriculaRequest request);

    MatriculaResponse confirmar(Integer id);

    MatriculaResponse anular(Integer id, String motivo);

    void eliminar(Integer id);

    boolean existeMatricula(Integer estudianteId, Integer grupoId);
}
