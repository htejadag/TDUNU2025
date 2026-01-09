package msposgrado.Service;

import msposgrado.Model.Revision;
import java.util.List;

/**
 * Interfaz de servicio para la entidad {@link Revision}.
 * 
 * Define los métodos de negocio para la gestión de revisiones de tesis,
 * incluyendo creación, consulta, listado y eliminación.
 */
public interface RevisionService {

    /**
     * Crea un nuevo registro de {@link Revision} en el sistema.
     * 
     * @param revision Objeto {@link Revision} a registrar.
     * @return La {@link Revision} creada, con su ID generado.
     */
    Revision crear(Revision revision);

    /**
     * Lista todos los registros de {@link Revision} existentes en el sistema.
     * 
     * @return Lista de {@link Revision}.
     */
    List<Revision> listar();

    /**
     * Obtiene un registro de {@link Revision} por su ID.
     * 
     * @param id ID de la revisión a consultar.
     * @return La {@link Revision} correspondiente al ID.
     */
    Revision obtenerPorId(Integer id);

    /**
     * Elimina un registro de {@link Revision} del sistema.
     * 
     * @param id ID de la revisión a eliminar.
     */
    void eliminar(Integer id);
}