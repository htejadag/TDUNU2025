package tdunu.MsAsistencia.service;

import tdunu.MsAsistencia.model.request.AsistenciaRequest;
import tdunu.MsAsistencia.model.response.AsistenciaResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface AsistenciaService {

    List<AsistenciaResponse> listar();

    AsistenciaResponse obtenerPorId(Integer id);

    AsistenciaResponse guardar(AsistenciaRequest request);

    void eliminar(Integer id);

    List<AsistenciaResponse> buscarPorEntidad(String tipoEntidad, Integer entidadId);

    List<AsistenciaResponse> buscarPorEvento(String tipoEvento, Integer eventoId);

    List<AsistenciaResponse> buscarPorFecha(LocalDateTime inicio, LocalDateTime fin);
}
