package tdunu.MsTitulacion.service;

import java.util.List;

import tdunu.MsTitulacion.model.request.ResolucionTituloRequest;
import tdunu.MsTitulacion.model.response.ResolucionTituloResponse;

public interface ResolucionTituloService {
    
    List<ResolucionTituloResponse> listar();
    ResolucionTituloResponse guardar(ResolucionTituloRequest request);
    ResolucionTituloResponse actualizar(int id, ResolucionTituloRequest request); //por seaca

    //no tendrá logica de eliminado logico ni fisico
    void eliminar (int id); //solo pruebas, no implementar
}
