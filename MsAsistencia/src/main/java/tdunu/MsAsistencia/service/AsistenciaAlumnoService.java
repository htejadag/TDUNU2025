package tdunu.MsAsistencia.service;

import tdunu.MsAsistencia.model.request.AsistenciaAlumnoRequest;
import tdunu.MsAsistencia.model.response.AsistenciaAlumnoResponse;

import java.util.List;

public interface AsistenciaAlumnoService {

    List<AsistenciaAlumnoResponse> listar();

    AsistenciaAlumnoResponse obtenerPorId(Integer id);

    AsistenciaAlumnoResponse guardar(AsistenciaAlumnoRequest request);

    void eliminar(Integer id);

    List<AsistenciaAlumnoResponse> buscarPorAlumno(Integer alumnoId);
}
