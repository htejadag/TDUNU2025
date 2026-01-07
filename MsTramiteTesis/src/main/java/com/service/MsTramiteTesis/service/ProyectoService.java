package com.service.MsTramiteTesis.service;

import com.service.MsTramiteTesis.model.dto.ProyectoRequest;
import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.dto.ProyectoResponseEnriquecido;
import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import com.service.MsTramiteTesis.model.Error.ResourceNotFoundException;
import com.service.MsTramiteTesis.repository.ProyectoRepository;

import com.service.MsTramiteTesis.kafka.producer.ProyectoEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProyectoEventProducer kafkaProducer;

    @Transactional
    public ProyectoResponse crearProyecto(Long idEstudiante, ProyectoRequest request) {
        ProyectoTesis proyecto = new ProyectoTesis();
        proyecto.setIdEstudianteExt(idEstudiante);
        proyecto.setIdAsesorExt(request.getIdAsesorExt());
        proyecto.setIdEspecialidadExt(request.getIdEspecialidadExt());
        proyecto.setTituloProyecto(request.getTituloProyecto());
        proyecto.setRutaPdfProyecto(request.getRutaPdfProyecto());
        proyecto.setEstadoProyectoCodigo("PENDIENTE_COORDINADOR");
        proyecto.setFechaRegistro(OffsetDateTime.now());

        ProyectoTesis saved = proyectoRepository.save(proyecto);

        try {
            kafkaProducer.publicarProyectoCreado(
                    saved.getIdProyecto().toString(),
                    saved.getTituloProyecto(),
                    "Proyecto creado",
                    saved.getEstadoProyectoCodigo());
            log.info("Evento Kafka CREADO publicado para proyecto ID: {}", saved.getIdProyecto());
        } catch (Exception e) {
            log.error("Error publicando evento Kafka: ", e);
        }

        return modelMapper.map(saved, ProyectoResponse.class);
    }

    @Transactional
    public ProyectoResponse actualizarPdf(Long idProyecto, Long idEstudiante, String rutaPdf) {
        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");

        ProyectoTesis proyecto = java.util.Objects.requireNonNull(
                proyectoRepository.findById(validatedId)
                        .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado")),
                "El proyecto no puede ser null");

        if (!proyecto.getIdEstudianteExt().equals(idEstudiante)) {
            throw new RuntimeException("No tienes permiso para modificar este proyecto");
        }

        proyecto.setRutaPdfProyecto(rutaPdf);
        proyecto.setEstadoProyectoCodigo("PENDIENTE_COORDINADOR");

        ProyectoTesis updated = proyectoRepository.save(proyecto);
        return modelMapper.map(updated, ProyectoResponse.class);
    }

    @Transactional
    public ProyectoResponse revisionCoordinador(Long idProyecto, Boolean aprobado) {
        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");

        ProyectoTesis proyecto = java.util.Objects.requireNonNull(
                proyectoRepository.findById(validatedId)
                        .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado")),
                "El proyecto no puede ser null");

        if (aprobado) {
            proyecto.setEstadoProyectoCodigo("APROBADO_COORDINADOR");
        } else {
            proyecto.setEstadoProyectoCodigo("RECHAZADO_COORDINADOR");
        }

        ProyectoTesis updated = proyectoRepository.save(proyecto);
        return modelMapper.map(updated, ProyectoResponse.class);
    }

    @Transactional
    public ProyectoResponse revisionAsesor(Long idProyecto, Long idAsesor, Boolean aprobado) {
        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");

        ProyectoTesis proyecto = java.util.Objects.requireNonNull(
                proyectoRepository.findById(validatedId)
                        .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado")),
                "El proyecto no puede ser null");

        if (!proyecto.getIdAsesorExt().equals(idAsesor)) {
            throw new RuntimeException("No eres el asesor de este proyecto");
        }

        if (aprobado) {
            proyecto.setEstadoProyectoCodigo("APROBADO_ASESOR");
            proyecto.setFechaAprobacionFinal(OffsetDateTime.now());
        } else {
            proyecto.setEstadoProyectoCodigo("RECHAZADO_ASESOR");
        }

        ProyectoTesis updated = proyectoRepository.save(proyecto);
        return modelMapper.map(updated, ProyectoResponse.class);
    }

    public List<ProyectoResponse> listarProyectosPorEstudiante(Long idEstudiante) {
        return proyectoRepository.findByIdEstudianteExt(idEstudiante)
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    public List<ProyectoResponse> listarProyectosPorAsesor(Long idAsesor) {
        return proyectoRepository.findByIdAsesorExt(idAsesor)
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    public List<ProyectoResponse> listarProyectosPendientesCoordinador() {
        return proyectoRepository.findByEstadoProyectoCodigo("PENDIENTE_COORDINADOR")
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    public ProyectoResponse obtenerProyecto(Long idProyecto) {
        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");

        ProyectoTesis proyecto = java.util.Objects.requireNonNull(
                proyectoRepository.findById(validatedId)
                        .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado")),
                "El proyecto no puede ser null");
        return modelMapper.map(proyecto, ProyectoResponse.class);
    }

    public List<ProyectoResponse> listarTodosProyectos() {
        return proyectoRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    public ProyectoResponseEnriquecido obtenerProyectoEnriquecido(Long idProyecto) {
        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");

        ProyectoTesis proyecto = java.util.Objects.requireNonNull(
                proyectoRepository.findById(validatedId)
                        .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado")),
                "El proyecto no puede ser null");

        return enriquecerProyecto(proyecto);
    }

    public List<ProyectoResponseEnriquecido> listarProyectosEnriquecidosPorEstudiante(Long idEstudiante) {
        return proyectoRepository.findByIdEstudianteExt(idEstudiante)
                .stream()
                .map(this::enriquecerProyecto)
                .collect(Collectors.toList());
    }

    public List<ProyectoResponseEnriquecido> listarProyectosEnriquecidosPorAsesor(Long idAsesor) {
        return proyectoRepository.findByIdAsesorExt(idAsesor)
                .stream()
                .map(this::enriquecerProyecto)
                .collect(Collectors.toList());
    }

    public List<ProyectoResponseEnriquecido> listarTodosProyectosEnriquecidos() {
        return proyectoRepository.findAll()
                .stream()
                .map(this::enriquecerProyecto)
                .collect(Collectors.toList());
    }

    private ProyectoResponseEnriquecido enriquecerProyecto(ProyectoTesis proyecto) {
        ProyectoResponseEnriquecido response = new ProyectoResponseEnriquecido();

        response.setIdProyecto(proyecto.getIdProyecto());
        response.setIdEstudianteExt(proyecto.getIdEstudianteExt());
        response.setIdAsesorExt(proyecto.getIdAsesorExt());
        response.setIdEspecialidadExt(proyecto.getIdEspecialidadExt());
        response.setTituloProyecto(proyecto.getTituloProyecto());
        response.setRutaPdfProyecto(proyecto.getRutaPdfProyecto());
        response.setEstadoProyectoCodigo(proyecto.getEstadoProyectoCodigo());
        response.setFechaRegistro(proyecto.getFechaRegistro());
        response.setFechaAprobacionFinal(proyecto.getFechaAprobacionFinal());

        return response;
    }

    @Transactional
    public ProyectoResponse actualizarEstadoTest(Long idProyecto, String estadoNuevo) {
        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");

        ProyectoTesis proyecto = java.util.Objects.requireNonNull(
                proyectoRepository.findById(validatedId)
                        .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado")),
                "El proyecto no puede ser null");

        String estadoAnterior = proyecto.getEstadoProyectoCodigo();
        proyecto.setEstadoProyectoCodigo(estadoNuevo);

        ProyectoTesis updated = proyectoRepository.save(proyecto);

        try {
            kafkaProducer.publicarProyectoActualizado(
                    updated.getIdProyecto().toString(),
                    estadoAnterior,
                    estadoNuevo);
            log.info("Evento Kafka ESTADO_CAMBIADO publicado: {} -> {}", estadoAnterior, estadoNuevo);
        } catch (Exception e) {
            log.error("Error publicando evento Kafka: ", e);
        }

        return modelMapper.map(updated, ProyectoResponse.class);
    }

    @Transactional
    public void eliminarProyectoTest(Long idProyecto) {
        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");

        ProyectoTesis proyecto = java.util.Objects.requireNonNull(
                proyectoRepository.findById(validatedId)
                        .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado")),
                "El proyecto no puede ser null");

        proyectoRepository.delete(java.util.Objects.requireNonNull(proyecto,
                "El proyecto a eliminar no puede ser null"));

        try {
            kafkaProducer.publicarProyectoEliminado(idProyecto.toString());
            log.info("Evento Kafka ELIMINADO publicado para proyecto ID: {}", idProyecto);
        } catch (Exception e) {
            log.error("Error publicando evento Kafka: ", e);
        }
    }
}
