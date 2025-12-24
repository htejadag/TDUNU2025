package com.service.MsTramiteTesis.service;

import com.service.MsTramiteTesis.model.dto.ProyectoRequest;
import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.dto.ProyectoResponseEnriquecido;
import com.service.MsTramiteTesis.model.dto.external.DocenteDTO;
import com.service.MsTramiteTesis.model.dto.external.EstudianteDTO;
import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import com.service.MsTramiteTesis.model.Error.ResourceNotFoundException;
import com.service.MsTramiteTesis.repository.ProyectoRepository;
import com.service.MsTramiteTesis.client.PersonasClient;
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
    private PersonasClient personasClient;

    @Autowired
    private ProyectoEventProducer kafkaProducer;

    /**
     * Crear un nuevo proyecto (ESTUDIANTE)
     */
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

        // 🔥 Publicar evento KAFKA
        try {
            kafkaProducer.publicarProyectoCreado(
                    saved.getIdProyecto().toString(),
                    saved.getTituloProyecto(),
                    "Proyecto creado",
                    saved.getEstadoProyectoCodigo());
            log.info("✅ Evento Kafka CREADO publicado para proyecto ID: {}", saved.getIdProyecto());
        } catch (Exception e) {
            log.error("❌ Error publicando evento Kafka: ", e);
        }

        return modelMapper.map(saved, ProyectoResponse.class);
    }

    /**
     * Actualizar PDF del proyecto (ESTUDIANTE - cuando es rechazado)
     */
    @Transactional
    public ProyectoResponse actualizarPdf(Long idProyecto, Long idEstudiante, String rutaPdf) {
        ProyectoTesis proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        // Validar que el proyecto pertenece al estudiante
        if (!proyecto.getIdEstudianteExt().equals(idEstudiante)) {
            throw new RuntimeException("No tienes permiso para modificar este proyecto");
        }

        proyecto.setRutaPdfProyecto(rutaPdf);
        proyecto.setEstadoProyectoCodigo("PENDIENTE_COORDINADOR");

        ProyectoTesis updated = proyectoRepository.save(proyecto);
        return modelMapper.map(updated, ProyectoResponse.class);
    }

    /**
     * Revisión del coordinador
     */
    @Transactional
    public ProyectoResponse revisionCoordinador(Long idProyecto, Boolean aprobado) {
        ProyectoTesis proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        if (aprobado) {
            proyecto.setEstadoProyectoCodigo("APROBADO_COORDINADOR");
        } else {
            proyecto.setEstadoProyectoCodigo("RECHAZADO_COORDINADOR");
        }

        ProyectoTesis updated = proyectoRepository.save(proyecto);
        return modelMapper.map(updated, ProyectoResponse.class);
    }

    /**
     * Revisión del asesor
     */
    @Transactional
    public ProyectoResponse revisionAsesor(Long idProyecto, Long idAsesor, Boolean aprobado) {
        ProyectoTesis proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        // Validar que el docente es el asesor del proyecto
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

    /**
     * Listar proyectos del estudiante
     */
    public List<ProyectoResponse> listarProyectosPorEstudiante(Long idEstudiante) {
        return proyectoRepository.findByIdEstudianteExt(idEstudiante)
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Listar proyectos del asesor
     */
    public List<ProyectoResponse> listarProyectosPorAsesor(Long idAsesor) {
        return proyectoRepository.findByIdAsesorExt(idAsesor)
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Listar proyectos pendientes para coordinador
     */
    public List<ProyectoResponse> listarProyectosPendientesCoordinador() {
        return proyectoRepository.findByEstadoProyectoCodigo("PENDIENTE_COORDINADOR")
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Obtener un proyecto por ID
     */
    public ProyectoResponse obtenerProyecto(Long idProyecto) {
        ProyectoTesis proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));
        return modelMapper.map(proyecto, ProyectoResponse.class);
    }

    /**
     * Listar todos los proyectos (para coordinador)
     */
    public List<ProyectoResponse> listarTodosProyectos() {
        return proyectoRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    // ========== MÉTODOS CON DATOS ENRIQUECIDOS ==========

    /**
     * Obtener un proyecto enriquecido con información del MS Personas
     */
    public ProyectoResponseEnriquecido obtenerProyectoEnriquecido(Long idProyecto) {
        ProyectoTesis proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        return enriquecerProyecto(proyecto);
    }

    /**
     * Listar proyectos enriquecidos del estudiante
     */
    public List<ProyectoResponseEnriquecido> listarProyectosEnriquecidosPorEstudiante(Long idEstudiante) {
        return proyectoRepository.findByIdEstudianteExt(idEstudiante)
                .stream()
                .map(this::enriquecerProyecto)
                .collect(Collectors.toList());
    }

    /**
     * Listar proyectos enriquecidos del asesor
     */
    public List<ProyectoResponseEnriquecido> listarProyectosEnriquecidosPorAsesor(Long idAsesor) {
        return proyectoRepository.findByIdAsesorExt(idAsesor)
                .stream()
                .map(this::enriquecerProyecto)
                .collect(Collectors.toList());
    }

    /**
     * Listar todos los proyectos enriquecidos (para coordinador)
     */
    public List<ProyectoResponseEnriquecido> listarTodosProyectosEnriquecidos() {
        return proyectoRepository.findAll()
                .stream()
                .map(this::enriquecerProyecto)
                .collect(Collectors.toList());
    }

    /**
     * Método auxiliar para enriquecer un proyecto con datos del MS Personas
     */
    private ProyectoResponseEnriquecido enriquecerProyecto(ProyectoTesis proyecto) {
        ProyectoResponseEnriquecido response = new ProyectoResponseEnriquecido();

        // Copiar datos básicos
        response.setIdProyecto(proyecto.getIdProyecto());
        response.setIdEstudianteExt(proyecto.getIdEstudianteExt());
        response.setIdAsesorExt(proyecto.getIdAsesorExt());
        response.setIdEspecialidadExt(proyecto.getIdEspecialidadExt());
        response.setTituloProyecto(proyecto.getTituloProyecto());
        response.setRutaPdfProyecto(proyecto.getRutaPdfProyecto());
        response.setEstadoProyectoCodigo(proyecto.getEstadoProyectoCodigo());
        response.setFechaRegistro(proyecto.getFechaRegistro());
        response.setFechaAprobacionFinal(proyecto.getFechaAprobacionFinal());

        // Enriquecer con datos del MS Personas
        EstudianteDTO estudiante = personasClient.obtenerEstudiante(proyecto.getIdEstudianteExt());
        response.setEstudiante(estudiante);

        DocenteDTO asesor = personasClient.obtenerDocente(proyecto.getIdAsesorExt());
        response.setAsesor(asesor);

        return response;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // MÉTODOS DE TESTING - SOLO PARA DESARROLLO
    // ⚠️ ELIMINAR ESTOS MÉTODOS EN PRODUCCIÓN ⚠️
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * 🧪 TESTING - Actualizar estado y publicar evento Kafka
     */
    @Transactional
    public ProyectoResponse actualizarEstadoTest(Long idProyecto, String estadoNuevo) {
        ProyectoTesis proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        String estadoAnterior = proyecto.getEstadoProyectoCodigo();
        proyecto.setEstadoProyectoCodigo(estadoNuevo);

        ProyectoTesis updated = proyectoRepository.save(proyecto);

        // 🔥 Publicar evento KAFKA
        try {
            kafkaProducer.publicarProyectoActualizado(
                    updated.getIdProyecto().toString(),
                    estadoAnterior,
                    estadoNuevo);
            log.info("✅ Evento Kafka ESTADO_CAMBIADO publicado: {} -> {}", estadoAnterior, estadoNuevo);
        } catch (Exception e) {
            log.error("❌ Error publicando evento Kafka: ", e);
        }

        return modelMapper.map(updated, ProyectoResponse.class);
    }

    /**
     * 🧪 TESTING - Eliminar proyecto y publicar evento Kafka
     */
    @Transactional
    public void eliminarProyectoTest(Long idProyecto) {
        ProyectoTesis proyecto = proyectoRepository.findById(idProyecto)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        proyectoRepository.delete(proyecto);

        // 🔥 Publicar evento KAFKA
        try {
            kafkaProducer.publicarProyectoEliminado(idProyecto.toString());
            log.info("✅ Evento Kafka ELIMINADO publicado para proyecto ID: {}", idProyecto);
        } catch (Exception e) {
            log.error("❌ Error publicando evento Kafka: ", e);
        }
    }
}
