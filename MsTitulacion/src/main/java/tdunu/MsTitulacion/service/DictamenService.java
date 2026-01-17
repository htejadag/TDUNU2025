package tdunu.MsTitulacion.service;

import java.util.List;

import tdunu.MsTitulacion.model.request.DictamenRequest;
import tdunu.MsTitulacion.model.response.DictamenResponse;

public interface DictamenService {
    
    List<DictamenResponse> listar();
    List<DictamenResponse> listarByResultadoCat(String categoria);
    DictamenResponse obtenerPorId(int id);
    DictamenResponse guardar(DictamenRequest request);
    DictamenResponse actualizar(int id, DictamenRequest request);

    //no tendrá logica de eliminado logico ni fisico
    void eliminar (int id);  //solo pruebas, no implementar
}
