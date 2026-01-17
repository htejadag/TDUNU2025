package com.service.MsTramiteTesis.Helper.KafkaHelpers;

import org.springframework.stereotype.Component;
import com.service.MsTramiteTesis.kafka.Event.NotificacionEvent;
import com.service.MsTramiteTesis.kafka.Producer.NotificacionProducer;
import com.service.MsTramiteTesis.model.entity.ProyectoTesis;
import com.service.MsTramiteTesis.model.enums.TipoEvento;
import com.service.MsTramiteTesis.repository.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificacionHelper {

    private final NotificacionProducer notificacionProducer;
    private final ProyectoRepository proyectoRepository;
    private static final String ID_USUARIO = "1";

    public void notificarProyectoCreado(ProyectoTesis proyecto) {
        String mensajeDetallado = String.format(
            "NOTIFICACION: NUEVO PROYECTO DE TESIS\n" +
            "----------------------------------\n" +
            "Evento: %s\n" +
            "Proyecto: %s (ID: %d)\n" +
            "Estudiante ID: %d\n" +
            "Estado: PENDIENTE DE REVISIÓN\n" +
            "Detalles: Se ha registrado un nuevo proyecto en el sistema. Proceder con la revision.",
            TipoEvento.PROYECTO_CREADO,
            proyecto.getTitulo(),
            proyecto.getIdProyecto(),
            proyecto.getIdEstudiante()
        );
        publicar(ID_USUARIO, mensajeDetallado);
    }

    public void notificarProyectoModificado(ProyectoTesis proyecto) {
        String mensajeDetallado = String.format(
            "NOTIFICACION: PROYECTO MODIFICADO\n" +
            "----------------------------------\n" +
            "Evento: %s\n" +
            "Proyecto: %s (ID: %d)\n" +
            "Estudiante ID: %d\n" +
            "Detalles: Se ha actualizado el archivo PDF del proyecto. Requiere nueva revision.",
            TipoEvento.PROYECTO_MODIFICADO,
            proyecto.getTitulo(),
            proyecto.getIdProyecto(),
            proyecto.getIdEstudiante()
        );
        publicar(ID_USUARIO, mensajeDetallado);
    }

    public void notificarRevisionCoordinador(ProyectoTesis proyecto, Boolean aprobado) {
        String resultado = aprobado ? "APROBADO" : "RECHAZADO";
        
        String msgEstudiante = String.format(
            "NOTIFICACION: REVISION DE COORDINACION\n" +
            "----------------------------------\n" +
            "Proyecto: %s\n" +
            "Resultado: %s\n" +
            "Detalles: Su proyecto ha sido %s por la coordinacion.",
            proyecto.getTitulo(),
            resultado,
            aprobado ? "aceptado" : "rechazado"
        );
        publicar(proyecto.getIdEstudiante().toString(), msgEstudiante);

        if (aprobado && proyecto.getIdAsesor() != null) {
            String msgAsesor = String.format(
                "NOTIFICACION: ASIGNACION DE ASESOR\n" +
                "----------------------------------\n" +
                "Proyecto: %s\n" +
                "Estudiante ID: %d\n" +
                "Detalles: Ha sido asignado como asesor del proyecto mencionado.",
                proyecto.getTitulo(),
                proyecto.getIdEstudiante()
            );
            publicar(ID_USUARIO, msgAsesor);
        }
    }

    public void notificarAsignacionJurados(Long idProyecto, List<Integer> idsDocentes) {
        proyectoRepository.findById(idProyecto).ifPresent(proyecto -> {
            for (Integer idDocente : idsDocentes) {
                String mensaje = String.format(
                    "NOTIFICACION: ASIGNACION DE JURADO\n" +
                    "----------------------------------\n" +
                    "Proyecto: %s\n" +
                    "ID Proyecto: %d\n" +
                    "Detalles: Ha sido designado como jurado para la evaluacion de este proyecto.",
                    proyecto.getTitulo(),
                    idProyecto
                );
                publicar(ID_USUARIO, mensaje);
            }
        });
    }

    public void notificarRevisionAsesor(ProyectoTesis proyecto, Boolean aprobado) {
        String estado = aprobado ? "APROBADO" : "OBSERVADO";
        String mensaje = String.format(
            "NOTIFICACION: REVISION DE ASESOR\n" +
            "----------------------------------\n" +
            "Proyecto: %s\n" +
            "Estado: %s\n" +
            "Detalles: Su asesor ha realizado la revision del proyecto.",
            proyecto.getTitulo(),
            estado
        );
        publicar(ID_USUARIO, mensaje);
    }

    public void notificarRevisionJurado(Long idProyecto, Integer idDocente, Integer resultadoCat) {
        proyectoRepository.findById(idProyecto).ifPresent(proyecto -> {
            boolean aprobado = (resultadoCat == 1);
            String calificacion = aprobado ? "APROBADO" : "OBSERVADO";

            String msgEstudiante = String.format(
                "NOTIFICACION: CALIFICACION DE JURADO\n" +
                "----------------------------------\n" +
                "Proyecto: %s\n" +
                "Resultado: %s\n" +
                "Detalles: Un jurado ha emitido su calificacion.",
                proyecto.getTitulo(),
                calificacion
            );
            publicar(proyecto.getIdEstudiante().toString(), msgEstudiante);

            String msgCoord = String.format(
                "NOTIFICACION: REVISION DE JURADO REGISTRADA\n" +
                "----------------------------------\n" +
                "Proyecto: %s (ID: %d)\n" +
                "Resultado: %s\n" +
                "Docente ID: %d",
                proyecto.getTitulo(),
                idProyecto,
                calificacion,
                idDocente
            );
            publicar(ID_USUARIO, msgCoord);
        });
    }

    private void publicar(String idUsuario, String cuerpo) {
        try {
            NotificacionEvent evento = NotificacionEvent.builder()
                    .idUsuario(idUsuario)
                    .mensaje(cuerpo)
                    .fechaCreacion(LocalDateTime.now())
                    .build();
            notificacionProducer.publish(evento);
        } catch (Exception e) {
            log.error("Error al publicar evento: {}", e.getMessage());
        }
    }
}