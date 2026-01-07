package tdunu.MsAsistencia.service;

import tdunu.MsAsistencia.model.request.JustificacionRequest;
import tdunu.MsAsistencia.model.response.JustificacionResponse;
import java.util.List;

public interface JustificacionService {

    List<JustificacionResponse> listar();

    JustificacionResponse obtenerPorId(Integer id);

    JustificacionResponse guardar(JustificacionRequest request);

    JustificacionResponse actualizar(Integer id, JustificacionRequest request);

    void eliminar(Integer id);

    JustificacionResponse aprobar(Integer id, Integer usuarioModificacion);

    JustificacionResponse rechazar(Integer id, Integer usuarioModificacion);

    List<JustificacionResponse> buscarPendientes();
}
