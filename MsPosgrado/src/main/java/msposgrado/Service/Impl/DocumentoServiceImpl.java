package msposgrado.Service.Impl;

import msposgrado.Model.Documento;
import msposgrado.Repository.DocumentoRepository;
import msposgrado.Service.DocumentoService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación del servicio {@link DocumentoService} para la gestión de documentos.
 *
 * Proporciona la lógica de negocio para crear, listar, obtener y eliminar documentos,
 * aplicando borrado lógico mediante el atributo {@code activo}.
 *
 * Utiliza el repositorio {@link DocumentoRepository} para interactuar con la base de datos.
 */
@Service
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository repository;

    /**
     * Constructor que inyecta el repositorio de documentos.
     *
     * @param repository Repositorio {@link DocumentoRepository} para acceso a datos.
     */
    public DocumentoServiceImpl(DocumentoRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea un nuevo documento en el sistema.
     *
     * @param documento Objeto {@link Documento} a crear.
     * @return El {@link Documento} creado con ID generado.
     */
    @Override
    public Documento crear(Documento documento) {
        return repository.save(documento);
    }

    /**
     * Lista todos los documentos activos.
     *
     * @return Lista de {@link Documento} cuyo atributo {@code activo} es {@code true}.
     */
    @Override
    public List<Documento> listar() {
        return repository.findByActivoTrue();
    }

    /**
     * Obtiene un documento por su ID.
     * Solo devuelve documentos que estén activos.
     *
     * @param id ID del documento a obtener.
     * @return El {@link Documento} correspondiente al ID, o {@code null} si no existe o está inactivo.
     */
    @Override
    public Documento obtenerPorId(Integer id) {
        return repository.findById(id).filter(Documento::getActivo).orElse(null);
    }

    /**
     * Elimina un documento mediante borrado lógico.
     * Marca el registro como inactivo sin eliminarlo físicamente.
     *
     * @param id ID del documento a eliminar.
     */
    @Override
    public void eliminar(Integer id) {
        Documento documento = obtenerPorId(id);
        if (documento != null) {
            documento.setActivo(false);
            repository.save(documento);
        }
    }
}