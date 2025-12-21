package tdunu.MsPersona.service;

import java.util.List;
import tdunu.MsPersona.model.request.DocenteRequest;
import tdunu.MsPersona.model.response.DocenteResponse;

public interface DocenteService {

    List<DocenteResponse> listar();
    
    DocenteResponse obtenerPorId(Integer id);
    
    DocenteResponse guardar(DocenteRequest request);
    
    DocenteResponse actualizar(Integer id, DocenteRequest request);
    
    void eliminar(Integer id);
}