package tdunu.MsTitulacion.service;

import java.util.List;

import tdunu.MsTitulacion.model.request.TesisBorradorRequest;
import tdunu.MsTitulacion.model.response.TesisBorradorResponse;

public interface TesisBorradorService {
    
    List<TesisBorradorResponse> listar();
    TesisBorradorResponse obtenerPorId(int id);
    TesisBorradorResponse guardar(TesisBorradorRequest request);
    TesisBorradorResponse actualizar(int id,TesisBorradorRequest request);

     boolean eliminar (int id);
}
