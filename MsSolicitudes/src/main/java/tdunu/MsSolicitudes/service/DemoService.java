package tdunu.MsSolicitudes.service;

import java.util.List;
import tdunu.MsSolicitudes.model.request.DemoRequest;
import tdunu.MsSolicitudes.model.response.DemoResponse;

public interface DemoService {

  DemoResponse crear(DemoRequest request);
    
    DemoResponse obtenerPorId(Integer id);
    
    List<DemoResponse> listarTodos();
    
    DemoResponse actualizar(Integer id, DemoRequest request);
    
    void eliminar(Integer id);
  
    List<DemoResponse> listarPorEstudiante(Integer idEstudiante);
    
    List<DemoResponse> listarPorEstado(String estado);
    
    List<DemoResponse> listarPorFase(String fase);
    
    DemoResponse obtenerPorCodigo(String codigo);

}