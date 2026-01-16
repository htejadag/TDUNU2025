package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.Auditoria;
import com.unu.ms.MsConsejo.repository.AuditoriaRepository;

/**
 * Implementación del servicio de auditoría para registrar cambios en el sistema.
 * 
 * Proporciona la lógica para registrar todas las acciones (CREATE, UPDATE, DELETE)
 * realizadas sobre las entidades principales del microservicio. Mantiene un registro
 * de auditoría completo con información del usuario, la entidad modificada y los
 * datos antes y después del cambio.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuditoriaServiceImp {

    /** Repositorio para acceso a datos de auditoría */
    private AuditoriaRepository auditoriaRepository;

    /**
     * Registra una acción de auditoría en el sistema.
     * 
     * Crea un registro de auditoría completo capturando la información de qué cambio
     * se realizó, quién lo realizó, en qué entidad, y los datos antes y después
     * del cambio. Esto permite mantener un historial completo de modificaciones
     * en el sistema.
     * 
     * @param nombreEntidad nombre de la entidad que fue modificada (ej: CONSEJO, MIEMBROS_CONSEJO, etc.)
     * @param entidadId identificador único de la entidad modificada
     * @param accion tipo de acción realizada (CREATE, UPDATE, DELETE)
     * @param usuarioId identificador del usuario que realizó la acción
     * @param datosAntes string con los datos de la entidad antes de la modificación (null para CREATE)
     * @param datosDespues string con los datos de la entidad después de la modificación (null para DELETE)
     * @param observacion comentario adicional sobre la acción realizada
     */
    public void registrarAccion(String nombreEntidad, Integer entidadId, String accion,
                                Integer usuarioId, String datosAntes, String datosDespues,
                                String observacion) {
        
        log.info("Inicio auditoria: registrar acción");
        log.debug("Entidad: {}, ID: {}, Acción: {}, Usuario: {}", nombreEntidad, entidadId, accion, usuarioId);
        
        Auditoria auditoria = new Auditoria();
        auditoria.setNombreEntidad(nombreEntidad);
        auditoria.setEntidadId(entidadId);
        auditoria.setAccion(accion);
        auditoria.setUsuarioId(usuarioId);
        auditoria.setDatosAntes(datosAntes);
        auditoria.setDatosDespues(datosDespues);
        auditoria.setObservacion(observacion);
        
        Auditoria registrado = auditoriaRepository.save(auditoria);
        
        log.info("Fin auditoria: acción registrada correctamente. ID Auditoría: {}", registrado.getIdAuditoria());
    }

}
