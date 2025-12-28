package Ms_Reingresante.Ms_Reingresante.service;

import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.request.ProcesoReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.ProcesoReingresoResponse;



public interface ProcesoReingresoService {

  List<ProcesoReingresoResponse> listarProcesoReingreso();

  ProcesoReingresoResponse obtenerPorIdProcesoReingreso(Integer id);

  ProcesoReingresoResponse guardarProcesoReingreso(ProcesoReingresoRequest producto);

  void eliminarProcesoReingreso(Integer id);


}
