package com.MsSimulacroExamen.ms_SesionSimulacro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.MsSimulacroExamen.ms_SesionSimulacro.entity.SesionSimulacro;
import com.MsSimulacroExamen.ms_SesionSimulacro.repository.SesionSimulacroRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class SesionSimulacroService {
    private final SesionSimulacroRepository sesionSimulacroRepository;
    private static final Logger log = LoggerFactory.getLogger(SesionSimulacroService.class);

    public SesionSimulacroService(SesionSimulacroRepository sesionSimulacroRepository) {
        this.sesionSimulacroRepository = sesionSimulacroRepository;
    }

    // Crear sesión
    public SesionSimulacro crearSesion(SesionSimulacro sesion) {
        // Aquí podrías agregar validaciones (fechas, horas, etc.)
        log.info("Guardando nueva sesión de simulacro en la base de datos: {}", sesion);
        return sesionSimulacroRepository.save(sesion);
    }

    // Listar todas las sesiones
    public List<SesionSimulacro> listarSesiones() {
        log.info("Consultando todas las sesiones de simulacro en la base de datos");
        return sesionSimulacroRepository.findAll();
    }

    // Obtener una sesión por ID
    public SesionSimulacro obtenerPorId(Long id) {
        log.info("Buscando sesión de simulacro con ID: {}", id);
        return sesionSimulacroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión de simulacro no encontrada con id: " + id));
    }

    // Actualizar sesión
    public SesionSimulacro actualizarSesion(Long id, SesionSimulacro sesionActualizada) {
        log.info("Iniciando actualización de sesión con ID: {}", id);
        SesionSimulacro existente = obtenerPorId(id);

        existente.setSimulacroId(sesionActualizada.getSimulacroId());
        existente.setNombreSesion(sesionActualizada.getNombreSesion());

        existente.setSedeId(sesionActualizada.getSedeId());
        existente.setAulaId(sesionActualizada.getAulaId());

        existente.setFecha(sesionActualizada.getFecha());
        existente.setHoraInicio(sesionActualizada.getHoraInicio());
        existente.setHoraFin(sesionActualizada.getHoraFin());

        existente.setCapacidadMaxima(sesionActualizada.getCapacidadMaxima());
        existente.setInscritos(sesionActualizada.getInscritos());

        existente.setEstado(sesionActualizada.getEstado());
        existente.setObservaciones(sesionActualizada.getObservaciones());

        existente.setUsuarioModificacion(sesionActualizada.getUsuarioModificacion());
        // fechaModificacion se actualiza sola por @UpdateTimestamp

        log.info("Guardando cambios de la sesión de simulacro con ID: {}", id);

        return sesionSimulacroRepository.save(existente);
    }

    // Eliminar sesión
    public void eliminarSesion(Long id) {
        log.info("Eliminando sesión de simulacro con ID: {}", id);
        SesionSimulacro existente = obtenerPorId(id);
        sesionSimulacroRepository.delete(existente);
    }
}
