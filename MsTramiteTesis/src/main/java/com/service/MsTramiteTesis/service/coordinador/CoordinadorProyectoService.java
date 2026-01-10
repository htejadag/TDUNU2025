package com.service.MsTramiteTesis.service.coordinador;

import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import com.service.MsTramiteTesis.model.entity.AsignacionJurado;
import com.service.MsTramiteTesis.model.Error.ResourceNotFoundException;
import com.service.MsTramiteTesis.repository.ProyectoRepository;
import com.service.MsTramiteTesis.repository.AsignacionJuradoRepository;
import com.service.MsTramiteTesis.config.CacheNames;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CoordinadorProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private AsignacionJuradoRepository asignacionJuradoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.CACHE_COORDINADOR_PENDIENTES, allEntries = true),
            @CacheEvict(value = CacheNames.CACHE_COORDINADOR_TODOS, allEntries = true),
            @CacheEvict(value = CacheNames.CACHE_COORDINADOR_PROYECTO, key = "#idProyecto")
    })
    public ProyectoResponse revisarProyecto(Long idProyecto, Boolean aprobado) {
        log.info("Coordinador revisando proyecto {}", idProyecto);

        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");
        ProyectoTesis proyecto = proyectoRepository.findById(validatedId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        if (aprobado) {
            proyecto.setEstadoProyectoCat(2); // APROBADO_COORDINADOR
            log.info("Proyecto {} aprobado por coordinador", idProyecto);
        } else {
            proyecto.setEstadoProyectoCat(3); // RECHAZADO_COORDINADOR
            log.info("Proyecto {} rechazado por coordinador", idProyecto);
        }

        ProyectoTesis updated = proyectoRepository.save(proyecto);
        return modelMapper.map(updated, ProyectoResponse.class);
    }

    @Cacheable(value = CacheNames.CACHE_COORDINADOR_PENDIENTES)
    public List<ProyectoResponse> listarProyectosPendientes() {
        log.info("Listando proyectos pendientes de revisión del coordinador");

        return proyectoRepository.findByEstadoProyectoCat(1) // PENDIENTE_COORDINADOR
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = CacheNames.CACHE_COORDINADOR_TODOS)
    public List<ProyectoResponse> listarTodosLosProyectos() {
        log.info("Listando todos los proyectos del sistema");

        return proyectoRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = CacheNames.CACHE_COORDINADOR_PROYECTO, key = "#idProyecto")
    public ProyectoResponse obtenerCualquierProyecto(Long idProyecto) {
        log.info("Coordinador obteniendo proyecto {}", idProyecto);

        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");
        ProyectoTesis proyecto = proyectoRepository.findById(validatedId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        return modelMapper.map(proyecto, ProyectoResponse.class);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.CACHE_COORDINADOR_JURADOS, key = "#idProyecto"),
            @CacheEvict(value = CacheNames.CACHE_DOCENTE_JURADO, allEntries = true)
    })
    public List<AsignacionJurado> asignarJurados(Long idProyecto, List<Integer> idsDocentes, Integer rolJuradoCat) {
        log.info("Asignando {} jurados al proyecto {}", idsDocentes.size(), idProyecto);

        // Verificar que el proyecto existe
        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");
        proyectoRepository.findById(validatedId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        List<AsignacionJurado> asignaciones = idsDocentes.stream()
                .map(idDocente -> {
                    AsignacionJurado asignacion = new AsignacionJurado();
                    asignacion.setIdProyecto(idProyecto);
                    asignacion.setIdDocente(idDocente);
                    asignacion.setRolJuradoCat(rolJuradoCat);
                    asignacion.setFechaSorteo(LocalDate.now());
                    asignacion.setActivo(true);
                    return asignacion;
                })
                .collect(Collectors.toList());

        List<AsignacionJurado> validatedList = java.util.Objects.requireNonNull(asignaciones,
                "Lista de asignaciones no puede ser null");
        List<AsignacionJurado> saved = asignacionJuradoRepository.saveAll(validatedList);
        log.info("Asignados {} jurados al proyecto {}", saved.size(), idProyecto);

        return saved;
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.CACHE_COORDINADOR_JURADOS, allEntries = true),
            @CacheEvict(value = CacheNames.CACHE_DOCENTE_JURADO, allEntries = true)
    })
    public void desactivarJurado(Long idAsignacion) {
        log.info("Desactivando asignación de jurado {}", idAsignacion);

        Long validatedId = java.util.Objects.requireNonNull(idAsignacion, "idAsignacion no puede ser null");
        AsignacionJurado asignacion = asignacionJuradoRepository.findById(validatedId)
                .orElseThrow(() -> new ResourceNotFoundException("Asignación no encontrada"));

        asignacion.setActivo(false);
        asignacionJuradoRepository.save(asignacion);
    }

    @Cacheable(value = CacheNames.CACHE_COORDINADOR_JURADOS, key = "#idProyecto")
    public List<AsignacionJurado> listarJuradosDeProyecto(Long idProyecto) {
        log.info("Listando jurados asignados al proyecto {}", idProyecto);
        return asignacionJuradoRepository.findByIdProyecto(idProyecto);
    }

}
