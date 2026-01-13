package tdunu.MsSeguridad.service;

import java.util.List;

import tdunu.MsSeguridad.model.request.SistemaRequest;
import tdunu.MsSeguridad.model.response.SistemaResponse;

public interface SistemaService {
    
    List<SistemaResponse> listar();
    SistemaResponse guardar(SistemaRequest request);
    SistemaResponse buscar(Long id);
    void eliminar(Long id);
    
}
