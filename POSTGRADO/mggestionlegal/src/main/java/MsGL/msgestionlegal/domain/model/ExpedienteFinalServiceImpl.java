package com.unu.epg.msgestionlegal.domain.model;

import com.unu.epg.msgestionlegal.domain.model.ExpedienteFinal;
import com.unu.epg.msgestionlegal.domain.model.EstadoExpediente;
import com.unu.epg.msgestionlegal.domain.repository.ExpedienteFinalRepository;
import com.unu.epg.msgestionlegal.service.ExpedienteFinalService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpedienteFinalServiceImpl implements ExpedienteFinalService {

    private final ExpedienteFinalRepository expedienteFinalRepository;

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

        if ("COMPLETADO".equals(nuevoEstado)) {
            expediente.setFechaFinalizacion(LocalDate.now());
        }

        return expedienteFinalRepository.save(expediente);
    }

    @Override
    public void eliminar(Long id) {
        expedienteFinalRepository.deleteById(id);
    }
}
