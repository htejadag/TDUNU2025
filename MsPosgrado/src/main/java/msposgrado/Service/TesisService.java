package msposgrado.Service;

import msposgrado.Model.Tesis;
import java.util.List;

/**
 * Interfaz de servicio para la entidad {@link Tesis}.
 * 
 * Define los métodos de negocio para la gestión de tesis dentro del sistema,
 * incluyendo creación, listado, consulta por ID y eliminación.
 */
public interface TesisService {

    /**
     * Crea un nuevo registro de {@link Tesis} en el sistema.
     * 
     * @param tesis Objeto {@link Tesis} a registrar.
     * @return La {@link Tesis} creada, con su ID generado.
     */
    Tesis crear(Tesis tesis);

    /**
     * Lista todas las tesis existentes en el sistema.
     * 
     * @return Lista de {@link Tesis}.
     */
    List<Tesis> listar();

    /**
     * Obtiene un registro de {@link Tesis} por su ID.
     * 
     * @param id ID de la tesis a consultar.
     * @return La {@link Tesis} correspondiente al ID.
     */
    Tesis obtenerPorId(Integer id);

    /**
     * Elimina un registro de {@link Tesis} del sistema.
     * 
     * @param id ID de la tesis a eliminar.
     */
    void eliminar(Integer id);
}