package Ms_Reingresante.Ms_Reingresante.service;

import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.request.InformeAcademicoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;




public interface InformeAcademicoService {

List<InformeAcademicoResponse> listarInformeAcademico();

  InformeAcademicoResponse obtenerPorIdInformeAcademico(Integer id);

  InformeAcademicoResponse guardarInformeAcademico(InformeAcademicoRequest producto);

  void eliminarInformeAcademico(Integer id);


}