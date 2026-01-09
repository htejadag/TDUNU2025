package msposgrado.Service;

import msposgrado.Model.Asesor;
import java.util.List;

/**
 * Interfaz de servicio para la entidad {@link Asesor}.
 * 
 * Define los métodos de negocio para la gestión de asesores,
 * incluyendo operaciones de creación, actualización, consulta y eliminación.
 */
public interface AsesorService {

    /**
     * Crea un nuevo asesor en el sistema.
     * 
     * @param asesor Objeto {@link Asesor} con los datos del nuevo asesor.
     * @return El {@link Asesor} creado, con su ID generado.
     */
    Asesor crear(Asesor asesor);

    /**
     * Actualiza los datos de un asesor existente.
     * 
     * @param id ID del asesor a actualizar.
     * @param asesor Objeto {@link Asesor} con los nuevos datos.
     * @return El {@link Asesor} actualizado.
     */
    Asesor actualizar(Integer id, Asesor asesor);

    /**
     * Lista todos los asesores registrados en el sistema.
     * 
     * @return Lista de {@link Asesor}.
     */
    List<Asesor> listar();

    /**
     * Obtiene un asesor por su ID.
     * 
     * @param id ID del asesor a consultar.
     * @return El {@link Asesor} correspondiente al ID.
     */
    Asesor obtenerPorId(Integer id);

    /**
     * Elimina un asesor del sistema (borrado lógico o físico según implementación).
     * 
     * @param id ID del asesor a eliminar.
     */
    void eliminar(Integer id);
}