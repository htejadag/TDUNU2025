package tdunu.MsTitulacion.service;

import java.util.List;

import tdunu.MsTitulacion.model.request.TesisBorradorRequest;
import tdunu.MsTitulacion.model.response.TesisBorradorResponse;

public interface TesisBorradorService {
    
    List<TesisBorradorResponse> listar();
    TesisBorradorResponse obtenerPorId();
    TesisBorradorResponse guardar(TesisBorradorRequest request);
    TesisBorradorResponse actualizar(TesisBorradorRequest request);

     boolean eliminar (String id);
}
