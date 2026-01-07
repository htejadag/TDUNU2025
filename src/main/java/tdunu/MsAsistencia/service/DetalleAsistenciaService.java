package tdunu.MsAsistencia.service;

import tdunu.MsAsistencia.model.request.DetalleAsistenciaRequest;
import tdunu.MsAsistencia.model.request.DetalleAsistenciaMasivoRequest;
import tdunu.MsAsistencia.model.response.DetalleAsistenciaResponse;
import java.util.List;

public interface DetalleAsistenciaService {

    List<DetalleAsistenciaResponse> listar();

    DetalleAsistenciaResponse obtenerPorId(Integer id);

    DetalleAsistenciaResponse guardar(DetalleAsistenciaRequest request);

    DetalleAsistenciaResponse actualizar(Integer id, DetalleAsistenciaRequest request, Integer usuarioModificacion);

    void eliminar(Integer id);

    List<DetalleAsistenciaResponse> buscarPorAsistencia(Integer asistenciaId);

    List<DetalleAsistenciaResponse> buscarPorEntidad(String tipoEntidad, Integer entidadId);

    List<DetalleAsistenciaResponse> registrarMasivo(DetalleAsistenciaMasivoRequest request);
}
