package msposgrado.Service;

import msposgrado.Model.Solicitud;
import java.util.List;

/**
 * Interfaz de servicio para la entidad {@link Solicitud}.
 * 
 * Define los métodos de negocio para la gestión de solicitudes dentro del sistema,
 * incluyendo creación, listado, consulta por ID y eliminación.
 */
public interface SolicitudService {

    /**
     * Crea un nuevo registro de {@link Solicitud} en el sistema.
     * 
     * @param solicitud Objeto {@link Solicitud} a registrar.
     * @return La {@link Solicitud} creada, con su ID generado.
     */
    Solicitud crear(Solicitud solicitud);

    /**
     * Lista todas las solicitudes existentes en el sistema.
     * 
     * @return Lista de {@link Solicitud}.
     */
    List<Solicitud> listar();

    /**
     * Obtiene un registro de {@link Solicitud} por su ID.
     * 
     * @param id ID de la solicitud a consultar.
     * @return La {@link Solicitud} correspondiente al ID.
     */
    Solicitud obtenerPorId(Integer id);

    /**
     * Elimina un registro de {@link Solicitud} del sistema.
     * 
     * @param id ID de la solicitud a eliminar.
     */
    void eliminar(Integer id);
}