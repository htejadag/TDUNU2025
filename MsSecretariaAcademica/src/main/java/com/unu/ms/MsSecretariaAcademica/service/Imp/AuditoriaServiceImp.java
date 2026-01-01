package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.Auditoria;
import com.unu.ms.MsSecretariaAcademica.repository.AuditoriaRepository;

/**
 * Implementación del servicio de auditoría.
 *
 * Este servicio se encarga de registrar las acciones realizadas
 * sobre las distintas entidades del microservicio, permitiendo
 * mantener un historial de auditoría para fines de trazabilidad
 * y control.
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuditoriaServiceImp {

    /**
     * Repositorio de auditoría.
     */
    AuditoriaRepository auditoriaRepository;

    /**
     * Registra una acción de auditoría sobre una entidad del sistema.
     *
     * Permite almacenar información sobre la entidad afectada,
     * el tipo de acción realizada, el usuario que la ejecutó
     * y los datos antes y después del cambio.
     *
     * @param nombreEntidad nombre de la entidad auditada
     * @param entidadId identificador de la entidad auditada
     * @param accion acción realizada (CREATE, UPDATE, DELETE, SOFT_DELETE)
     * @param usuarioId identificador del usuario que realizó la acción
     * @param datosAntes representación de los datos antes del cambio
     * @param datosDespues representación de los datos después del cambio
     * @param observacion observación adicional asociada a la acción
     */
    public void registrarAccion(
            String nombreEntidad,
            Integer entidadId,
            String accion,
            Integer usuarioId,
            String datosAntes,
            String datosDespues,
            String observacion
    ) {
        Auditoria auditoria = new Auditoria();
        auditoria.setNombreEntidad(nombreEntidad);
        auditoria.setEntidadId(entidadId);
        auditoria.setAccion(accion);
        auditoria.setUsuarioId(usuarioId);
        auditoria.setDatosAntes(datosAntes);
        auditoria.setDatosDespues(datosDespues);
        auditoria.setObservacion(observacion);

        auditoriaRepository.save(auditoria);
    }

}
