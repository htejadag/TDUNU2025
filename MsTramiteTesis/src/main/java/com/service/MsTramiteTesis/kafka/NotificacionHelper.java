package com.service.MsTramiteTesis.kafka;

import org.springframework.stereotype.Component;

import com.service.MsTramiteTesis.model.dto.NotificacionEventDTO;
import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import com.service.MsTramiteTesis.model.enums.RolUsuario;
import com.service.MsTramiteTesis.model.enums.TipoEvento;
import com.service.MsTramiteTesis.repository.ProyectoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificacionHelper {

    private final NotificacionProducer notificacionProducer;
    private final ProyectoRepository proyectoRepository;

    // ==================== NOTIFICACIONES DE ESTUDIANTE ====================

    /**
     * Notifica al coordinador cuando se crea un nuevo proyecto
     */
    public void notificarProyectoCreado(ProyectoTesis proyecto) {
        try {
            NotificacionEventDTO evento = NotificacionEventDTO.builder()
                    .tipoEvento(TipoEvento.PROYECTO_CREADO)
                    .idEstudiante(proyecto.getIdEstudiante())
                    .idProyecto(proyecto.getIdProyecto())
                    .rolDestinatario(RolUsuario.COORDINADOR)
                    .titulo("Nuevo proyecto de tesis")
                    .mensaje("Se ha creado un nuevo proyecto: " + proyecto.getTitulo())
                    .detalles("Proyecto ID: " + proyecto.getIdProyecto() + " - Estado: Pendiente de revisión")
                    .build();

            notificacionProducer.enviarEventoAsync(evento);
        } catch (Exception e) {
            log.warn("No se pudo enviar notificación de proyecto creado", e);
        }
    }

    /**
     * Notifica al coordinador cuando se modifica un proyecto
     */
    public void notificarProyectoModificado(ProyectoTesis proyecto) {
        try {
            NotificacionEventDTO evento = NotificacionEventDTO.builder()
                    .tipoEvento(TipoEvento.PROYECTO_MODIFICADO)
                    .idEstudiante(proyecto.getIdEstudiante())
                    .idProyecto(proyecto.getIdProyecto())
                    .rolDestinatario(RolUsuario.COORDINADOR)
                    .titulo("Proyecto actualizado")
                    .mensaje("Se ha actualizado el PDF del proyecto: " + proyecto.getTitulo())
                    .detalles("Proyecto ID: " + proyecto.getIdProyecto() + " - Estado: Pendiente de revisión")
                    .build();

            notificacionProducer.enviarEventoAsync(evento);
        } catch (Exception e) {
            log.warn("No se pudo enviar notificación de proyecto modificado", e);
        }
    }

    // ==================== NOTIFICACIONES DE COORDINADOR ====================

    /**
     * Notifica al estudiante y asesor sobre la revisión del coordinador
     */
    public void notificarRevisionCoordinador(ProyectoTesis proyecto, Boolean aprobado) {
        try {
            // Notificar al estudiante
            NotificacionEventDTO evento = NotificacionEventDTO.builder()
                    .tipoEvento(aprobado
                            ? TipoEvento.PROYECTO_APROBADO_COORDINADOR
                            : TipoEvento.PROYECTO_RECHAZADO_COORDINADOR)
                    .idEstudiante(proyecto.getIdEstudiante())
                    .idAsesor(proyecto.getIdAsesor())
                    .idProyecto(proyecto.getIdProyecto())
                    .rolDestinatario(RolUsuario.ESTUDIANTE)
                    .titulo(aprobado ? "Proyecto aprobado por coordinador" : "Proyecto rechazado por coordinador")
                    .mensaje(aprobado
                            ? "Tu proyecto '" + proyecto.getTitulo() + "' ha sido aprobado por el coordinador"
                            : "Tu proyecto '" + proyecto.getTitulo() + "' ha sido rechazado por el coordinador")
                    .detalles("Proyecto ID: " + proyecto.getIdProyecto())
                    .build();

            notificacionProducer.enviarEventoAsync(evento);

            // También notificar al asesor si fue aprobado
            if (aprobado && proyecto.getIdAsesor() != null) {
                NotificacionEventDTO eventoAsesor = NotificacionEventDTO.builder()
                        .tipoEvento(TipoEvento.ASESOR_ASIGNADO)
                        .idEstudiante(proyecto.getIdEstudiante())
                        .idAsesor(proyecto.getIdAsesor())
                        .idProyecto(proyecto.getIdProyecto())
                        .rolDestinatario(RolUsuario.DOCENTE)
                        .titulo("Asignado como asesor de proyecto")
                        .mensaje("Has sido asignado como asesor del proyecto: " + proyecto.getTitulo())
                        .detalles("Proyecto ID: " + proyecto.getIdProyecto())
                        .build();

                notificacionProducer.enviarEventoAsync(eventoAsesor);
            }
        } catch (Exception e) {
            log.warn("No se pudo enviar notificación de revisión de coordinador", e);
        }
    }

    /**
     * Notifica a los jurados asignados a un proyecto
     */
    public void notificarAsignacionJurados(Long idProyecto, List<Integer> idsDocentes) {
        try {
            ProyectoTesis proyecto = proyectoRepository.findById(idProyecto).orElse(null);

            if (proyecto != null) {
                for (Integer idDocente : idsDocentes) {
                    NotificacionEventDTO evento = NotificacionEventDTO.builder()
                            .tipoEvento(TipoEvento.NOTIFICACION_GENERAL)
                            .idProyecto(idProyecto)
                            .rolDestinatario(RolUsuario.DOCENTE)
                            .titulo("Asignado como jurado")
                            .mensaje("Has sido asignado como jurado del proyecto: " + proyecto.getTitulo())
                            .detalles("Proyecto ID: " + idProyecto + " - Estudiante ID: " + proyecto.getIdEstudiante())
                            .build();

                    notificacionProducer.enviarEventoAsync(evento);
                }
            }
        } catch (Exception e) {
            log.warn("No se pudo enviar notificación de asignación de jurados", e);
        }
    }

    // ==================== NOTIFICACIONES DE DOCENTE ====================

    /**
     * Notifica al estudiante sobre la revisión del asesor
     */
    public void notificarRevisionAsesor(ProyectoTesis proyecto, Boolean aprobado) {
        try {
            NotificacionEventDTO evento = NotificacionEventDTO.builder()
                    .tipoEvento(aprobado
                            ? TipoEvento.REVISION_ASESOR_APROBADA
                            : TipoEvento.REVISION_ASESOR_OBSERVADA)
                    .idEstudiante(proyecto.getIdEstudiante())
                    .idAsesor(proyecto.getIdAsesor())
                    .idProyecto(proyecto.getIdProyecto())
                    .rolDestinatario(RolUsuario.ESTUDIANTE)
                    .titulo(aprobado ? "Proyecto aprobado por asesor" : "Proyecto observado por asesor")
                    .mensaje(aprobado
                            ? "Tu proyecto '" + proyecto.getTitulo() + "' ha sido aprobado por tu asesor"
                            : "Tu proyecto '" + proyecto.getTitulo() + "' tiene observaciones del asesor")
                    .detalles("Proyecto ID: " + proyecto.getIdProyecto())
                    .build();

            notificacionProducer.enviarEventoAsync(evento);
        } catch (Exception e) {
            log.warn("No se pudo enviar notificación de revisión de asesor", e);
        }
    }

    /**
     * Notifica al estudiante y coordinador sobre la revisión del jurado
     */
    public void notificarRevisionJurado(Long idProyecto, Integer idDocente, Integer resultadoCat) {
        try {
            ProyectoTesis proyecto = proyectoRepository.findById(idProyecto).orElse(null);

            if (proyecto != null) {
                boolean aprobado = resultadoCat == 1;

                // Notificar al estudiante
                NotificacionEventDTO evento = NotificacionEventDTO.builder()
                        .tipoEvento(aprobado
                                ? TipoEvento.REVISION_JURADO_APROBADA
                                : TipoEvento.REVISION_JURADO_OBSERVADA)
                        .idEstudiante(proyecto.getIdEstudiante())
                        .idProyecto(idProyecto)
                        .rolDestinatario(RolUsuario.ESTUDIANTE)
                        .titulo(aprobado ? "Revisión de jurado aprobada" : "Revisión de jurado con observaciones")
                        .mensaje(aprobado
                                ? "Un jurado ha aprobado tu proyecto: " + proyecto.getTitulo()
                                : "Un jurado ha realizado observaciones a tu proyecto: " + proyecto.getTitulo())
                        .detalles("Proyecto ID: " + idProyecto + " - Revisa los comentarios del jurado")
                        .build();

                notificacionProducer.enviarEventoAsync(evento);

                // Notificar al coordinador
                NotificacionEventDTO eventoCoordinador = NotificacionEventDTO.builder()
                        .tipoEvento(TipoEvento.NOTIFICACION_GENERAL)
                        .idProyecto(idProyecto)
                        .idEstudiante(proyecto.getIdEstudiante())
                        .rolDestinatario(RolUsuario.COORDINADOR)
                        .titulo("Nueva revisión de jurado")
                        .mensaje("Se ha registrado una nueva revisión de jurado para el proyecto: "
                                + proyecto.getTitulo())
                        .detalles("Proyecto ID: " + idProyecto + " - Resultado: "
                                + (aprobado ? "Aprobado" : "Con observaciones"))
                        .build();

                notificacionProducer.enviarEventoAsync(eventoCoordinador);
            }
        } catch (Exception e) {
            log.warn("No se pudo enviar notificación de revisión de jurado", e);
        }
    }
}
