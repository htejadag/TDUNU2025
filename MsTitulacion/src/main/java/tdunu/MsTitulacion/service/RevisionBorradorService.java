package tdunu.MsTitulacion.service;

import java.util.List;

import tdunu.MsTitulacion.model.request.RevisionBorradorRequest;
import tdunu.MsTitulacion.model.response.RevisionBorradorResponse;

public interface RevisionBorradorService {
    
    List<RevisionBorradorResponse> listar();
    RevisionBorradorResponse guardar(RevisionBorradorRequest request);
    RevisionBorradorResponse actualizar(RevisionBorradorRequest request); //por seaca

    //no tendrá logica de eliminado logico ni fisico
    boolean eliminar(int id); //solo pruebas, no implementar

}
