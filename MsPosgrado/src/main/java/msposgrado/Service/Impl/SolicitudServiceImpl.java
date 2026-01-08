package msposgrado.Service.Impl;

import msposgrado.Model.Solicitud;
import msposgrado.Repository.SolicitudRepository;
import msposgrado.Service.SolicitudService;
import org.springframework.kafka.core.KafkaTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public SolicitudServiceImpl(SolicitudRepository repository, KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Solicitud crear(Solicitud solicitud) {
        Solicitud nuevaSolicitud = repository.save(solicitud);
        try {
            String json = objectMapper.writeValueAsString(nuevaSolicitud);
            kafkaTemplate.send("solicitudes-events", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevaSolicitud;
    }

    @Override
    public List<Solicitud> listar() {
        return repository.findByActivoTrue();
    }

    @Override
    public Solicitud obtenerPorId(Integer id) {
        return repository.findById(id).filter(Solicitud::getActivo).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        Solicitud solicitud = obtenerPorId(id);
        if (solicitud != null) {
            solicitud.setActivo(false);
            repository.save(solicitud);
        }
    }
}
