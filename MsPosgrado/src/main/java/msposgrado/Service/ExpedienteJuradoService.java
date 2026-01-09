package msposgrado.Service;

import msposgrado.Model.ExpedienteJurado;
import java.util.List;

/**
 * Interfaz de servicio para la entidad {@link ExpedienteJurado}.
 * 
 * Define los métodos de negocio para la gestión de la relación
 * entre expedientes y jurados, incluyendo operaciones de creación,
 * consulta, listado y eliminación.
 */
public interface ExpedienteJuradoService {

    /**
     * Crea un nuevo registro de {@link ExpedienteJurado} en el sistema.
     * 
     * @param ej Objeto {@link ExpedienteJurado} a registrar.
     * @return El {@link ExpedienteJurado} creado, con su ID generado.
     */
    ExpedienteJurado crear(ExpedienteJurado ej);

    /**
     * Lista todos los registros de {@link ExpedienteJurado} existentes en el sistema.
     * 
     * @return Lista de {@link ExpedienteJurado}.
     */
    List<ExpedienteJurado> listar();

    /**
     * Obtiene un registro de {@link ExpedienteJurado} por su ID.
     * 
     * @param id ID del registro a consultar.
     * @return El {@link ExpedienteJurado} correspondiente al ID.
     */
    ExpedienteJurado obtenerPorId(Integer id);

    /**
     * Elimina un registro de {@link ExpedienteJurado} del sistema.
     * 
     * @param id ID del registro a eliminar.
     */
    void eliminar(Integer id);
}