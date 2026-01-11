package tdunu.MsTitulacion.service;

import java.util.List;

import tdunu.MsTitulacion.model.request.ResolucionTituloRequest;
import tdunu.MsTitulacion.model.response.ResolucionTituloResponse;

public interface ResolucionTituloService {
    
    List<ResolucionTituloResponse> listar();
    ResolucionTituloResponse guardar(ResolucionTituloRequest request);
    ResolucionTituloResponse actualizar(ResolucionTituloRequest request); //por seaca

    //no tendrá logica de eliminado logico ni fisico
    boolean eliminar (int id); //solo pruebas, no implementar
}
