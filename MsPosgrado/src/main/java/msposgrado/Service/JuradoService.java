package msposgrado.Service;

import msposgrado.Model.Jurado;
import java.util.List;

/**
 * Interfaz de servicio para la entidad {@link Jurado}.
 * 
 * Define los métodos de negocio para la gestión de jurados,
 * incluyendo creación, consulta, actualización, listado y eliminación.
 */
public interface JuradoService {

    /**
     * Crea un nuevo registro de {@link Jurado} en el sistema.
     * 
     * @param jurado Objeto {@link Jurado} a registrar.
     * @return El {@link Jurado} creado, con su ID generado.
     */
    Jurado crear(Jurado jurado);

    /**
     * Lista todos los registros de {@link Jurado} existentes en el sistema.
     * 
     * @return Lista de {@link Jurado}.
     */
    List<Jurado> listar();

    /**
     * Obtiene un registro de {@link Jurado} por su ID.
     * 
     * @param id ID del jurado a consultar.
     * @return El {@link Jurado} correspondiente al ID.
     */
    Jurado obtenerPorId(Integer id);

    /**
     * Actualiza un registro de {@link Jurado} existente.
     * 
     * @param id ID del jurado a actualizar.
     * @param jurado Objeto {@link Jurado} con los datos nuevos.
     * @return El {@link Jurado} actualizado.
     */
    Jurado actualizar(Integer id, Jurado jurado);

    /**
     * Elimina un registro de {@link Jurado} del sistema.
     * 
     * @param id ID del jurado a eliminar.
     */
    void eliminar(Integer id);
}