package com.MsSimulacroExamen.ms_SesionSimulacro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.MsSimulacroExamen.ms_SesionSimulacro.entity.SesionSimulacro;
import com.MsSimulacroExamen.ms_SesionSimulacro.repository.SesionSimulacroRepository;


@Service
public class SesionSimulacroService {
    private final SesionSimulacroRepository sesionSimulacroRepository;

    public SesionSimulacroService(SesionSimulacroRepository sesionSimulacroRepository) {
        this.sesionSimulacroRepository = sesionSimulacroRepository;
    }

    // Crear sesión
    public SesionSimulacro crearSesion(SesionSimulacro sesion) {
        // Aquí podrías agregar validaciones (fechas, horas, etc.)
        return sesionSimulacroRepository.save(sesion);
    }

    // Listar todas las sesiones
    public List<SesionSimulacro> listarSesiones() {
        return sesionSimulacroRepository.findAll();
    }

    // Obtener una sesión por ID
    public SesionSimulacro obtenerPorId(Long id) {
        return sesionSimulacroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión de simulacro no encontrada con id: " + id));
    }

    // Actualizar sesión
    public SesionSimulacro actualizarSesion(Long id, SesionSimulacro sesionActualizada) {
        SesionSimulacro existente = obtenerPorId(id);

        existente.setIdSimulacro(sesionActualizada.getIdSimulacro());
        existente.setFecha(sesionActualizada.getFecha());
        existente.setHoraInicio(sesionActualizada.getHoraInicio());
        existente.setHoraFin(sesionActualizada.getHoraFin());
        existente.setAula(sesionActualizada.getAula());
        existente.setSede(sesionActualizada.getSede());
        existente.setEstado(sesionActualizada.getEstado());

        return sesionSimulacroRepository.save(existente);
    }

    // Eliminar sesión
    public void eliminarSesion(Long id) {
        SesionSimulacro existente = obtenerPorId(id);
        sesionSimulacroRepository.delete(existente);
    }
}
