package com.service.MsTramiteTesis.service.estudiante;

import com.service.MsTramiteTesis.model.dto.ProyectoRequest;
import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import com.service.MsTramiteTesis.model.Error.ResourceNotFoundException;
import com.service.MsTramiteTesis.repository.ProyectoRepository;
import com.service.MsTramiteTesis.config.CacheNames;
import com.service.MsTramiteTesis.kafka.NotificacionHelper;

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
public class EstudianteProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificacionHelper notificacionHelper;

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.CACHE_ESTUDIANTE_PROYECTOS, key = "#idEstudiante")
    })
    public ProyectoResponse crearProyecto(Integer idEstudiante, ProyectoRequest request) {
        log.info("Estudiante {} creando nuevo proyecto: {}", idEstudiante, request.getTitulo());

        ProyectoTesis proyecto = new ProyectoTesis();
        proyecto.setIdEstudiante(idEstudiante);
        proyecto.setIdAsesor(request.getIdAsesor());
        proyecto.setIdLinea(request.getIdLinea());
        proyecto.setTitulo(request.getTitulo());
        proyecto.setRutaArchivoProyecto(request.getRutaArchivoProyecto());
        proyecto.setEstadoProyectoCat(1); // PENDIENTE_COORDINADOR
        proyecto.setFechaRegistro(LocalDateTime.now());

        ProyectoTesis saved = proyectoRepository.save(proyecto);
        log.info("Proyecto {} creado exitosamente", saved.getIdProyecto());

        // Enviar notificación al coordinador
        notificacionHelper.notificarProyectoCreado(saved);

        return modelMapper.map(saved, ProyectoResponse.class);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.CACHE_ESTUDIANTE_PROYECTO, key = "#idProyecto"),
            @CacheEvict(value = CacheNames.CACHE_ESTUDIANTE_PROYECTOS, key = "#idEstudiante")
    })
    public ProyectoResponse actualizarPdf(Long idProyecto, Integer idEstudiante, String rutaPdf) {
        log.info("Estudiante {} actualizando PDF del proyecto {}", idEstudiante, idProyecto);

        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");
        ProyectoTesis proyecto = proyectoRepository.findById(validatedId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        if (!proyecto.getIdEstudiante().equals(idEstudiante)) {
            throw new RuntimeException("No tienes permiso para modificar este proyecto");
        }

        proyecto.setRutaArchivoProyecto(rutaPdf);
        proyecto.setEstadoProyectoCat(1); // Vuelve a PENDIENTE_COORDINADOR

        ProyectoTesis updated = proyectoRepository.save(proyecto);

        // Enviar notificación al coordinador
        notificacionHelper.notificarProyectoModificado(updated);

        return modelMapper.map(updated, ProyectoResponse.class);
    }

    @Cacheable(value = CacheNames.CACHE_ESTUDIANTE_PROYECTOS, key = "#idEstudiante")
    public List<ProyectoResponse> listarMisProyectos(Integer idEstudiante) {
        log.info("Listando proyectos del estudiante {}", idEstudiante);

        return proyectoRepository.findByIdEstudiante(idEstudiante)
                .stream()
                .map(p -> modelMapper.map(p, ProyectoResponse.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = CacheNames.CACHE_ESTUDIANTE_PROYECTO, key = "#idProyecto")
    public ProyectoResponse obtenerMiProyecto(Long idProyecto, Integer idEstudiante) {
        log.info("Estudiante {} obteniendo proyecto {}", idEstudiante, idProyecto);

        Long validatedId = java.util.Objects.requireNonNull(idProyecto, "idProyecto no puede ser null");
        ProyectoTesis proyecto = proyectoRepository.findById(validatedId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        if (!proyecto.getIdEstudiante().equals(idEstudiante)) {
            throw new RuntimeException("No tienes permiso para ver este proyecto");
        }

        return modelMapper.map(proyecto, ProyectoResponse.class);
    }

}
