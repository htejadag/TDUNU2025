package Ms_Reingresante.Ms_Reingresante.service;

import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.request.procesoReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.procesoReingresoResponse;



public interface ProcesoReingresoService {

  List<procesoReingresoResponse> listarProcesoReingreso();

  procesoReingresoResponse obtenerPorIdProcesoReingreso(Integer id);

  procesoReingresoResponse guardarProcesoReingreso(procesoReingresoRequest producto);

  void eliminarProcesoReingreso(Integer id);


}
