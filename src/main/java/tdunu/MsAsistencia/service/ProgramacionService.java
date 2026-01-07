package tdunu.MsAsistencia.service;

import tdunu.MsAsistencia.model.request.ProgramacionRequest;
import tdunu.MsAsistencia.model.response.ProgramacionResponse;
import java.util.List;

public interface ProgramacionService {

    List<ProgramacionResponse> listar();

    ProgramacionResponse obtenerPorId(Integer id);

    ProgramacionResponse guardar(ProgramacionRequest request);

    ProgramacionResponse actualizar(Integer id, ProgramacionRequest request);

    void eliminar(Integer id);

    List<ProgramacionResponse> buscarPorSistema(String sistemaOrigen);

    List<ProgramacionResponse> buscarPorTipo(String tipoProgramacion);

    List<ProgramacionResponse> buscarPorContexto(String tipoContexto, Integer contextoId);
}
