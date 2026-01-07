package tdunu.MsAsistencia.service;

import tdunu.MsAsistencia.model.response.HistorialAsistenciaResponse;
import java.util.List;

public interface HistorialAsistenciaService {

    List<HistorialAsistenciaResponse> listar();

    HistorialAsistenciaResponse obtenerPorId(Integer id);

    List<HistorialAsistenciaResponse> buscarPorDetalle(Integer detalleAsistenciaId);

    void registrarCambio(Integer detalleAsistenciaId, String estadoAnterior, String estadoNuevo, String motivo, Integer usuarioCreacion);
}
