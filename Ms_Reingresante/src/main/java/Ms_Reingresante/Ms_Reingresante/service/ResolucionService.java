package Ms_Reingresante.Ms_Reingresante.service;


import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.request.ResolucionRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.ResolucionResponse;




public interface ResolucionService {


  List<ResolucionResponse> listarResolucion();

  ResolucionResponse obtenerPorIdResolucion(Integer id);

  ResolucionResponse guardarResolucion(ResolucionRequest producto);

  void eliminarResolucion(Integer id);

}