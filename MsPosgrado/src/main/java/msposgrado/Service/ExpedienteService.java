package msposgrado.Service;

import msposgrado.Model.Expediente;
import java.util.List;

/**
 * Interfaz de servicio para la entidad {@link Expediente}.
 * 
 * Define los métodos de negocio para la gestión de expedientes,
 * incluyendo creación, consulta, actualización, listado y eliminación.
 */
public interface ExpedienteService {

    /**
     * Crea un nuevo registro de {@link Expediente} en el sistema.
     * 
     * @param expediente Objeto {@link Expediente} a registrar.
     * @return El {@link Expediente} creado, con su ID generado.
     */
    Expediente crear(Expediente expediente);

    /**
     * Lista todos los registros de {@link Expediente} existentes en el sistema.
     * 
     * @return Lista de {@link Expediente}.
     */
    List<Expediente> listar();

    /**
     * Obtiene un registro de {@link Expediente} por su ID.
     * 
     * @param id ID del expediente a consultar.
     * @return El {@link Expediente} correspondiente al ID.
     */
    Expediente obtenerPorId(Integer id);

    /**
     * Actualiza un registro de {@link Expediente} existente.
     * 
     * @param id ID del expediente a actualizar.
     * @param expediente Objeto {@link Expediente} con los datos nuevos.
     * @return El {@link Expediente} actualizado.
     */
    Expediente actualizar(Integer id, Expediente expediente);

    /**
     * Elimina un registro de {@link Expediente} del sistema.
     * 
     * @param id ID del expediente a eliminar.
     */
    void eliminar(Integer id);
}