package MsGL.MS_Gestion_Legal.Service.Impl;

import MsGL.MS_Gestion_Legal.Service.ExpedienteFinalService;
import MsGL.MS_Gestion_Legal.domain.model.ExpedienteFinal;
import MsGL.MS_Gestion_Legal.domain.model.EstadoExpediente;
import MsGL.MS_Gestion_Legal.domain.repository.ExpedientefinalRepository;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpedienteFinalServiceImpl implements ExpedienteFinalService {

    private final ExpedientefinalRepository expedienteFinalRepository;

    @Override
    public ExpedienteFinal crear(ExpedienteFinal ex) {
        return expedienteFinalRepository.save(ex);
    }

    @Override
    public List<ExpedienteFinal> listar() {
        return expedienteFinalRepository.findAll();
    }

    @Override
    public Optional<ExpedienteFinal> buscarPorId(Long id) {
        return expedienteFinalRepository.findById(id);
    }

    @Override
    public List<ExpedienteFinal> buscarPorIdEstudiante(Long idEstudiante) {
        return expedienteFinalRepository.findByIdEstudiante(idEstudiante);
    }

    @Override
    public ExpedienteFinal actualizarEstado(Long id, String nuevoEstado, String observaciones) {
        ExpedienteFinal expediente = expedienteFinalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expediente no encontrado con ID: " + id));

        expediente.setEstado(EstadoExpediente.valueOf(nuevoEstado));

        if (observaciones != null && !observaciones.trim().isEmpty()) {
            expediente.setObservaciones(observaciones);
        }

        if (nuevoEstado.equals("COMPLETADO")) {
            expediente.setFechaFinalizacion(LocalDate.now());
        }

        return expedienteFinalRepository.save(expediente);
    }

    @Override
    public void eliminar(Long id) {
        expedienteFinalRepository.deleteById(id);
    }
}
