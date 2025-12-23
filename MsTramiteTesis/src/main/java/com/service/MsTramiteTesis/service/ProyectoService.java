package com.service.MsTramiteTesis.service;

import com.service.MsTramiteTesis.model.dto.ProyectoRequest;
import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import com.service.MsTramiteTesis.model.Error.ResourceNotFoundException;
import com.service.MsTramiteTesis.repository.ProyectoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ModelMapper modelMapper;

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
}
