package Ms_Reingresante.Ms_Reingresante.service;

import java.util.List;

import Ms_Reingresante.Ms_Reingresante.model.request.FichaNoAdeudoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.FichaNoAdeudoResponse;


public interface FichaNoAdeudoService {

    /**
     * Lista todas las Fichas de No Adeudo registradas.
     * @return Lista de objetos FichaNoAdeudoResponse.
     */
    List<FichaNoAdeudoResponse> listar();

    /**
     * Obtiene una Ficha por su identificador único.
     * Se usa Long porque el ID de la Entity es Long.
     * @param id El ID de la ficha.
     * @return El objeto FichaNoAdeudoResponse.
     */
    FichaNoAdeudoResponse obtenerPorId(Long id);

    /**
     * Genera y guarda la Ficha de No Adeudo para un proceso de reingreso,
     * incluyendo la lógica de verificación de deudas.
     * @param request Datos de la solicitud.
     * @return La Ficha de No Adeudo generada.
     */
    FichaNoAdeudoResponse generarFicha(FichaNoAdeudoRequest request);

    /**
     * Actualiza el estado de la Ficha a "APROBADA" si cumple con los requisitos.
     * @param id El ID de la ficha a aprobar.
     * @return La ficha actualizada.
     */
    FichaNoAdeudoResponse aprobarFicha(Long id);
}