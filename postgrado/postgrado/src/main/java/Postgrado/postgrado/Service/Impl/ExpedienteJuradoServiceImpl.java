package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.ExpedienteJurado;
import Postgrado.postgrado.Repository.ExpedienteJuradoRepository;
import Postgrado.postgrado.Service.ExpedienteJuradoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpedienteJuradoServiceImpl implements ExpedienteJuradoService {

    private final ExpedienteJuradoRepository repository;
    private final Postgrado.postgrado.Service.Kafka.ProducerService producerService;

    public ExpedienteJuradoServiceImpl(ExpedienteJuradoRepository repository,
            Postgrado.postgrado.Service.Kafka.ProducerService producerService) {
        this.repository = repository;
        this.producerService = producerService;
    }

    @Override
    public ExpedienteJurado crear(ExpedienteJurado ej) {
        ExpedienteJurado saved = repository.save(ej);
        try {
            // Emitir evento JuradoAsignado
            String message = String.format(
                    "{\"event\": \"JuradoAsignado\", \"idAsignacion\": %d, \"idJurado\": %d, \"idExpediente\": %d}",
                    saved.getIdExpJurado(), saved.getJurado().getIdJurado(),
                    saved.getExpediente().getIdExpediente());
            producerService.sendMessage("postgrado_events", message);
        } catch (Exception e) {
            System.err.println("Error enviando mensaje a Kafka: " + e.getMessage());
        }
        return saved;
    }

    @Override
    public List<ExpedienteJurado> listar() {
        return repository.findByActivoTrue();
    }

    @Override
    public ExpedienteJurado obtenerPorId(Integer id) {
        return repository.findById(id).filter(ExpedienteJurado::getActivo).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        ExpedienteJurado ej = obtenerPorId(id);
        if (ej != null) {
            ej.setActivo(false);
            repository.save(ej);
        }
    }
}
