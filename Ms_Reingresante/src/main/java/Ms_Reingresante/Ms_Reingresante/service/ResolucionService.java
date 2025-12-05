package Ms_Reingresante.Ms_Reingresante.service;


import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.request.resolucionRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.resolucionResponse;




public interface ResolucionService {


  List<resolucionResponse> listar();

  resolucionResponse obtenerPorId(Integer id);

  resolucionResponse guardar(resolucionRequest producto);

  void eliminar(Integer id);

}