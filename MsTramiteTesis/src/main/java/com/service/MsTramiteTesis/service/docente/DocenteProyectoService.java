package com.service.MsTramiteTesis.service.docente;

import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import com.service.MsTramiteTesis.model.entity.RevisionProyecto;
import com.service.MsTramiteTesis.model.entity.AsignacionJurado;
import com.service.MsTramiteTesis.model.Error.ResourceNotFoundException;
import com.service.MsTramiteTesis.repository.ProyectoRepository;
import com.service.MsTramiteTesis.repository.RevisionProyectoRepository;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocenteProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private RevisionProyectoRepository revisionProyectoRepository;

    @Autowired
    private AsignacionJuradoRepository asignacionJuradoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private com.service.MsTramiteTesis.kafka.NotificacionHelper notificacionHelper;

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.CACHE_DOCENTE_ASESORIAS, key = "#idAsesor"),
            @CacheEvict(value = CacheNames.CACHE_COORDINADOR_PENDIENTES, allEntries = true),
            @CacheEvict(value = CacheNames.CACHE_COORDINADOR_TODOS, allEntries = true)
    })
    public ProyectoResponse revisarComoAsesor(Long idProyecto, Integer idAsesor, Boolean aprobado) {
        log.info("Docente {} revisando proyecto {} como asesor", idAsesor, idProyecto);

        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");
        ProyectoTesis proyecto = proyectoRepository.findById(validatedId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        if (!proyecto.getIdAsesor().equals(idAsesor)) {
            throw new RuntimeException("No eres el asesor de este proyecto");
        }

        if (aprobado) {
            proyecto.setEstadoProyectoCat(4); // APROBADO_ASESOR
            log.info("Proyecto {} aprobado por el asesor", idProyecto);
        } else {
            proyecto.setEstadoProyectoCat(5); // RECHAZADO_ASESOR
            log.info("Proyecto {} rechazado por el asesor", idProyecto);
        }

        ProyectoTesis updated = proyectoRepository.save(proyecto);

        // Enviar notificaciones
        notificacionHelper.notificarRevisionAsesor(updated, aprobado);

        return modelMapper.map(updated, ProyectoResponse.class);
    }

    @Cacheable(value = CacheNames.CACHE_DOCENTE_ASESORIAS, key = "#idAsesor")
    public List<ProyectoResponse> listarProyectosQueAsesoro(Integer idAsesor) {
        log.info("Listando proyectos que asesora el docente {}", idAsesor);

        return proyectoRepository.findByIdAsesor(idAsesor)
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = CacheNames.CACHE_DOCENTE_JURADO, key = "#idDocente")
    public List<ProyectoResponse> listarProyectosComoJurado(Integer idDocente) {
        log.info("Listando proyectos asignados al jurado {}", idDocente);

        List<AsignacionJurado> asignaciones = asignacionJuradoRepository
                .findByIdDocenteAndActivo(idDocente, true);

        return asignaciones.stream()
                .map(asignacion -> {
                    Long idProyecto = java.util.Objects.requireNonNull(asignacion.getIdProyecto(),
                            "idProyecto no puede ser null");
                    return proyectoRepository.findById(idProyecto);
                })
                .filter(optional -> optional.isPresent())
                .map(optional -> modelMapper.map(optional.get(), ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @CacheEvict(value = CacheNames.CACHE_DOCENTE_REVISIONES, key = "#idProyecto")
    public RevisionProyecto revisarComoJurado(
            Long idProyecto,
            Integer idDocente,
            Integer rolJuradoCat,
            String comentarios,
            Integer resultadoCat) {

        log.info("Jurado {} revisando proyecto {}", idDocente, idProyecto);

        // Verificar que el docente está asignado como jurado
        List<AsignacionJurado> asignaciones = asignacionJuradoRepository
                .findByIdProyecto(idProyecto);

        boolean esJurado = asignaciones.stream()
                .anyMatch(a -> a.getIdDocente().equals(idDocente) && a.getActivo());

        if (!esJurado) {
            throw new RuntimeException("No estás asignado como jurado de este proyecto");
        }

        // Crear o actualizar revisión
        RevisionProyecto revision = new RevisionProyecto();
        revision.setIdProyecto(idProyecto);
        revision.setIdRevisorDocente(idDocente);
        revision.setRolRevisorCat(rolJuradoCat);
        revision.setComentarios(comentarios);
        revision.setResultadoCat(resultadoCat);
        revision.setFechaRevision(LocalDateTime.now());

        RevisionProyecto saved = revisionProyectoRepository.save(revision);
        log.info("Revisión {} creada para proyecto {}", saved.getIdRevision(), idProyecto);

        // Enviar notificación de revisión de jurado
        notificacionHelper.notificarRevisionJurado(idProyecto, idDocente, resultadoCat);

        return saved;
    }

    @Cacheable(value = CacheNames.CACHE_DOCENTE_REVISIONES, key = "#idProyecto")
    public List<RevisionProyecto> obtenerRevisionesDeProyecto(Long idProyecto) {
        log.info("Obteniendo revisiones del proyecto {}", idProyecto);
        return revisionProyectoRepository.findByIdProyecto(idProyecto);
    }
}
