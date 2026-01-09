package msposgrado.Service.Impl;

import msposgrado.Model.Solicitud;
import msposgrado.Repository.SolicitudRepository;
import msposgrado.Service.SolicitudService;
import org.springframework.kafka.core.KafkaTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import jakarta.persistence.EntityManager;

/**
 * Implementación del servicio {@link SolicitudService} para la gestión de solicitudes.
 *
 * Gestiona operaciones CRUD sobre {@link Solicitud} y envía notificaciones
 * de creación a un tópico Kafka.
 */
@Service
@Transactional
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    /**
     * Constructor que inyecta las dependencias necesarias.
     *
     * @param repository Repositorio para operaciones de {@link Solicitud}.
     * @param kafkaTemplate Template para enviar mensajes a Kafka.
     * @param objectMapper Mapeador JSON para serializar objetos.
     * @param entityManager EntityManager para operaciones de persistencia avanzadas.
     */
    public SolicitudServiceImpl(SolicitudRepository repository,
                                KafkaTemplate<String, String> kafkaTemplate,
                                ObjectMapper objectMapper,
                                EntityManager entityManager) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.entityManager = entityManager;
    }

    /**
     * Crea una nueva solicitud y la envía a Kafka.
     *
     * @param solicitud Objeto {@link Solicitud} a crear.
     * @return La {@link Solicitud} creada y persistida en la base de datos.
     */
    @Override
    public Solicitud crear(Solicitud solicitud) {
        Solicitud nuevaSolicitud = repository.save(solicitud);
        entityManager.refresh(nuevaSolicitud); // Forzar refresco desde la BD
        try {
            String json = objectMapper.writeValueAsString(nuevaSolicitud);
            System.out.println("DEBUG KAFKA JSON: " + json);
            kafkaTemplate.send("solicitudes-events", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevaSolicitud;
    }

    /**
     * Lista todas las solicitudes activas.
     *
     * @return Lista de {@link Solicitud} con atributo {@code activo = true}.
     */
    @Override
    public List<Solicitud> listar() {
        return repository.findByActivoTrue();
    }

    /**
     * Obtiene una solicitud por su ID solo si está activa.
     *
     * @param id ID de la solicitud a obtener.
     * @return La {@link Solicitud} correspondiente o {@code null} si no existe o está inactiva.
     */
    @Override
    public Solicitud obtenerPorId(Integer id) {
        return repository.findById(id).filter(Solicitud::getActivo).orElse(null);
    }

    /**
     * Realiza un borrado lógico de la solicitud marcando su atributo {@code activo} como {@code false}.
     *
     * @param id ID de la solicitud a eliminar.
     */
    @Override
    public void eliminar(Integer id) {
        Solicitud solicitud = obtenerPorId(id);
        if (solicitud != null) {
            solicitud.setActivo(false);
            repository.save(solicitud);
        }
    }
}