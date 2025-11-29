package com.proyect.MsSustentacion.Service.impl;

import com.proyect.MsSustentacion.Repository.SustentacionRepository;
import com.proyect.MsSustentacion.Service.SustentacionService;
import com.proyect.MsSustentacion.model.Entity.Sustentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SustentacionServiceImpl implements SustentacionService {

    @Autowired
    private SustentacionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Sustentacion> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Sustentacion findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la sustentación con el ID: " + id));
    }

    @Override
    public Sustentacion save(Sustentacion sustentacion) {

        // --- LÓGICA COMPLEJA (Que @Valid no puede hacer sola) ---

        // 1. Validar unicidad de Acta (requiere ir a BD)
        if (sustentacion.getActaNumero() != null && !sustentacion.getActaNumero().isEmpty()) {
            boolean actaExiste = (sustentacion.getId() == null)
                    ? repository.existsByActaNumero(sustentacion.getActaNumero())
                    : repository.existsByActaNumeroAndIdNot(sustentacion.getActaNumero(), sustentacion.getId());

            if (actaExiste) {
                throw new RuntimeException("El número de acta ya existe.");
            }
        }

        // 2. Valores por defecto
        if (sustentacion.getEstadoResulId() == null) {
            sustentacion.setEstadoResulId((short) 1);
        }

        // 3. Validación fecha personalizada (Solo si es nuevo)
        if (sustentacion.getId() == null) {
            LocalDateTime fechaProg = LocalDateTime.of(sustentacion.getFecha(), sustentacion.getHora());
            if (fechaProg.isBefore(LocalDateTime.now())) {
                throw new RuntimeException("La fecha no puede ser en el pasado.");
            }
        }

        return repository.save(sustentacion);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. La sustentación con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }
}
