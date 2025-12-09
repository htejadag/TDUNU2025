package Ms_Reingresante.Ms_Reingresante.service;

import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.request.InformeAcademicoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;


public interface InformeAcademicoService {

    /**
     * Lista todos los Informes Académicos registrados.
     * @return Lista de objetos InformeAcademicoResponse.
     */
    List<InformeAcademicoResponse> listar();

    /**
     * Obtiene un Informe por su identificador único.
     * Se usa Long porque el ID de la Entity es Long.
     * @param id El ID del informe.
     * @return El objeto InformeAcademicoResponse.
     */
    InformeAcademicoResponse obtenerPorId(Long id);

    /**
     * Genera el Informe Académico para un proceso de reingreso, 
     * consultando el historial académico del estudiante.
     * @param request Datos de la solicitud.
     * @return El Informe Académico generado.
     */
    InformeAcademicoResponse generarInforme(InformeAcademicoRequest request);
}