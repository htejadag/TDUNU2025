package msposgrado.Service;

import msposgrado.Model.Documento;
import java.util.List;

/**
 * Interfaz de servicio para la entidad {@link Documento}.
 * 
 * Define los métodos de negocio para la gestión de documentos,
 * incluyendo operaciones de creación, consulta, listado y eliminación.
 */
public interface DocumentoService {

    /**
     * Crea un nuevo documento en el sistema.
     * 
     * @param documento Objeto {@link Documento} con los datos del documento a registrar.
     * @return El {@link Documento} creado, con su ID generado.
     */
    Documento crear(Documento documento);

    /**
     * Lista todos los documentos registrados en el sistema.
     * 
     * @return Lista de {@link Documento}.
     */
    List<Documento> listar();

    /**
     * Obtiene un documento por su ID.
     * 
     * @param id ID del documento a consultar.
     * @return El {@link Documento} correspondiente al ID.
     */
    Documento obtenerPorId(Integer id);

    /**
     * Elimina un documento del sistema (borrado lógico o físico según implementación).
     * 
     * @param id ID del documento a eliminar.
     */
    void eliminar(Integer id);
}