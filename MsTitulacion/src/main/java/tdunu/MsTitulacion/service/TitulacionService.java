package tdunu.MsTitulacion.service;

import tdunu.MsTitulacion.model.request.DictamenPostgradoRequest;
import tdunu.MsTitulacion.model.response.ActaAprobacionResponse;


public interface TitulacionService {

    ActaAprobacionResponse procesarDictamenYGenerarActa(DictamenPostgradoRequest request);
    ActaAprobacionResponse obtenerActaPorProyecto(Integer idProyecto);

}