package com.proyect.MsSustentacion.Service.impl;

import com.proyect.MsSustentacion.Repository.SustentacionRepository;
import com.proyect.MsSustentacion.Service.SustentacionService;
import com.proyect.MsSustentacion.model.Entity.Sustentacion;
import com.proyect.MsSustentacion.model.Error.BusinessRuleException;
import com.proyect.MsSustentacion.model.Error.ResourceNotFoundException;
import com.proyect.MsSustentacion.model.request.SustentacionRequest;
import com.proyect.MsSustentacion.model.response.SustentacionResponse;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SustentacionServiceImpl implements SustentacionService {

    @Autowired
    private SustentacionRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SustentacionResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, SustentacionResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SustentacionResponse findById(Long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, SustentacionResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la sustentación con ID: " + id));
    }

    @Override
    @Transactional
    public SustentacionResponse save(SustentacionRequest request) {

        log.info("Intentando guardar sustentación para tramiteId: {}", request.getTramiteId());

        // 1. Request -> Entity
        Sustentacion sustentacion = modelMapper.map(request, Sustentacion.class);

        // --- 2. VALIDACIONES DE NEGOCIO ---

        // A) Integridad (Opcional si ya usas @NotNull en el Request)
        if (sustentacion.getTramiteId() == null) {
            throw new BusinessRuleException("El ID del trámite es obligatorio.");
        }

        // B) Validar Fecha Futura (Solo creación)
        if (sustentacion.getId() == null) {
            LocalDateTime fechaProg = LocalDateTime.of(sustentacion.getFecha(), sustentacion.getHora());
            if (fechaProg.isBefore(LocalDateTime.now())) {
                throw new BusinessRuleException("La fecha y hora de sustentación no pueden ser en el pasado.");
            }
        }

        // C) Unicidad de Acta
        if (sustentacion.getActaNumero() != null && !sustentacion.getActaNumero().isEmpty()) {
            boolean existe = (sustentacion.getId() == null)
                    ? repository.existsByActaNumero(sustentacion.getActaNumero())
                    : repository.existsByActaNumeroAndIdNot(sustentacion.getActaNumero(), sustentacion.getId());

            if (existe) {
                throw new BusinessRuleException("El número de acta '" + sustentacion.getActaNumero() + "' ya existe.");
            }
        }

        // D) Valores por defecto
        if (sustentacion.getEstadoResulId() == null) {
            sustentacion.setEstadoResulId((short) 1);
        }

        // --- 3. Guardar ---
        Sustentacion saved = repository.save(sustentacion);

        log.info("Sustentación guardada con éxito. ID: {}", saved.getId());

        // --- 4. Entity -> Response ---
        return modelMapper.map(saved, SustentacionResponse.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. No existe el ID: " + id);
        }
        repository.deleteById(id);
    }
}