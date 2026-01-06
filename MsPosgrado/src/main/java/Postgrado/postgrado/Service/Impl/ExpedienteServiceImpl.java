package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Expediente;
import Postgrado.postgrado.Repository.ExpedienteRepository;
import Postgrado.postgrado.Service.ExpedienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpedienteServiceImpl implements ExpedienteService {

    private final ExpedienteRepository repository;

    public ExpedienteServiceImpl(ExpedienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Expediente crear(Expediente expediente) {
        if (expediente.getEstadoExpediente() == null) {
            expediente.setEstadoExpediente("INICIADO");
        }
        return repository.save(expediente);
    }

    @Override
    public List<Expediente> listar() {
        return repository.findByActivoTrue();
    }

    @Override
    public Expediente obtenerPorId(Integer id) {
        Expediente expediente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expediente no encontrado"));

        if (!expediente.getActivo()) {
            throw new RuntimeException("Expediente no encontrado");
        }
        return expediente;
    }

    @Override
    public Expediente actualizar(Integer id, Expediente data) {
        Expediente existente = obtenerPorId(id);
        if (existente == null)
            return null;

        if (data.getIdEstudiante() != null) {
            existente.setIdEstudiante(data.getIdEstudiante());
        }
        if (data.getEstadoExpediente() != null && !data.getEstadoExpediente().isEmpty()) {
            existente.setEstadoExpediente(data.getEstadoExpediente());
        }
        if (data.getFechaCierre() != null) {
            existente.setFechaCierre(data.getFechaCierre());
        }
        if (data.getObservaciones() != null && !data.getObservaciones().isEmpty()) {
            existente.setObservaciones(data.getObservaciones());
        }

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Expediente existente = obtenerPorId(id);
        if (existente != null) {
            existente.setActivo(false);
            repository.save(existente);
        }
    }
}
