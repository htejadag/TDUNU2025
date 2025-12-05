package Ms_Reingresante.Ms_Reingresante.service;


import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.request.resolucionRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.resolucionResponse;




public interface ResolucionService {


  List<resolucionResponse> listarResolucion();

  resolucionResponse obtenerPorIdResolucion(Integer id);

  resolucionResponse guardarResolucion(resolucionRequest producto);

  void eliminarResolucion(Integer id);

}