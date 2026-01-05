package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Solicitud;
import Postgrado.postgrado.Repository.SolicitudRepository;
import Postgrado.postgrado.Service.Kafka.ProducerService;
import Postgrado.postgrado.Service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ProducerService producerService;

    // Se mantiene el constructor original, pero ahora hace referencia a
    // 'solicitudRepository'
    // en lugar de 'repository' para alinearse con el nuevo nombre del campo.
    // Si se adopta completamente la inyección de campos, este constructor podría
    // volverse redundante.
    public SolicitudServiceImpl(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public Solicitud crear(Solicitud solicitud) {
        Solicitud savedSolicitud = solicitudRepository.save(solicitud);
        try {
            // Emitir evento de Solicitud Registrada
            String message = String.format(
                    "{\"event\": \"SolicitudRegistrada\", \"idSolicitud\": %d, \"tipoSolicitud\": \"%s\", \"estado\": \"%s\"}",
                    savedSolicitud.getIdSolicitud(), savedSolicitud.getTipoSolicitud(),
                    savedSolicitud.getEstadoSolicitud());
            producerService.sendMessage("postgrado_events", message);
        } catch (Exception e) {
            // Registrar error pero no interrumpir la transacción
            System.err.println("Error enviando mensaje a Kafka: " + e.getMessage());
        }
        return savedSolicitud;
    }

    @Override
    public List<Solicitud> listar() {
        return solicitudRepository.findByActivoTrue();
    }

    @Override
    public Solicitud obtenerPorId(Integer id) {
        return solicitudRepository.findById(id).filter(Solicitud::getActivo).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        Solicitud solicitud = obtenerPorId(id);
        if (solicitud != null) {
            solicitud.setActivo(false);
            solicitudRepository.save(solicitud);
        }
    }
}
