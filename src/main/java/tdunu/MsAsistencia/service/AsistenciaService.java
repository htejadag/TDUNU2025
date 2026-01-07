package tdunu.MsAsistencia.service;

import tdunu.MsAsistencia.model.request.AsistenciaRequest;
import tdunu.MsAsistencia.model.response.AsistenciaResponse;
import java.time.LocalDate;
import java.util.List;

public interface AsistenciaService {

    List<AsistenciaResponse> listar();

    AsistenciaResponse obtenerPorId(Integer id);

    AsistenciaResponse guardar(AsistenciaRequest request);

    AsistenciaResponse actualizar(Integer id, AsistenciaRequest request);

    void eliminar(Integer id);

    List<AsistenciaResponse> buscarPorProgramacion(Integer programacionId);

    List<AsistenciaResponse> buscarPorFecha(LocalDate fecha);
}
