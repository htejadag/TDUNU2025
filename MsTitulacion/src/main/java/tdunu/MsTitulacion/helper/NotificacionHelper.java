package tdunu.MsTitulacion.helper;

import java.time.LocalDateTime;

// IMPORTANTE: Asegúrate de importar la clase correcta donde la tengas definida
// Si es una clase compartida, ajusta el paquete según corresponda
import tdunu.MsTitulacion.kafka.event.NotificacionEvent; 
import tdunu.MsTitulacion.kafka.producer.NotificacionProducer;
import tdunu.MsTitulacion.model.entity.Dictamen;
import tdunu.MsTitulacion.model.entity.RevisionBorrador;
import tdunu.MsTitulacion.model.entity.TesisBorrador;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificacionHelper {

    private final NotificacionProducer producer;

    // ============================================================
    // SECCIÓN 1: NOTIFICACIONES DE BORRADOR
    // ============================================================

    public void notificarSubidaBorrador(TesisBorrador borrador, boolean esCorreccion) {
        // Construimos un mensaje claro concatenando los datos
        String accion = esCorreccion ? "correcciones subidas" : "nuevo borrador subido";
        String mensaje = String.format("Trámite Tesis: Se ha registrado %s (ID Borrador: %d). Pendiente de revisión.", 
                                       accion, borrador.getIdTesisBorrador());

        // Como no tenemos el ID del estudiante a mano en el objeto TesisBorrador (solo idProyecto),
        // enviamos un identificador genérico o el del proyecto para que MS_GENERAL sepa a quién buscar.
        String idReferencia = "PROYECTO-" + borrador.getIdProyecto();

        enviarEvento(idReferencia, mensaje);
    }

    public void notificarResultadoRevision(RevisionBorrador revision) {
        String estado = revision.isAprobado() ? "APROBADO" : "OBSERVADO";
        String cuerpo = revision.isAprobado() 
            ? "Felicidades, tu borrador ha sido aprobado por el jurado." 
            : "Tu borrador tiene observaciones pendientes. Revisa el sistema.";

        String mensaje = String.format("Trámite Tesis: Resultado de Revisión %s. %s", estado, cuerpo);

        // Aquí usamos el ID del jurado o borrador como referencia
        enviarEvento("JURADO-" + revision.getIdJurado(), mensaje);
    }

    // ============================================================
    // SECCIÓN 2: NOTIFICACIONES DE DICTAMEN
    // ============================================================

    public void notificarProgramacionSustentacion(Dictamen dictamen) {
        // Formateamos la fecha para que sea legible en el mensaje
        String fechaLegible = dictamen.getFechaHora().toString().replace("T", " ");
        
        String mensaje = String.format("URGENTE: Tu sustentación ha sido programada para el %s en el lugar: %s.", 
                                       fechaLegible, dictamen.getAulaLugar());

        enviarEvento("DICTAMEN-" + dictamen.getIdDictamen(), mensaje);
    }

    public void notificarResultadoFinal(Dictamen dictamen) {
        String mensaje = String.format("Trámite Finalizado: Acta cerrada. Tu nota final de sustentación es: %s.", 
                                       dictamen.getNotaFinal());

        enviarEvento("DICTAMEN-" + dictamen.getIdDictamen(), mensaje);
    }

    // ============================================================
    // MÉTODO PRIVADO ADAPTADO A TU ESTRUCTURA
    // ============================================================
    
    private void enviarEvento(String idUsuarioReferencia, String mensajeTexto) {
        try {
            // Construimos el evento EXACTAMENTE con los 3 campos que tienes
            NotificacionEvent event = NotificacionEvent.builder()
                .idUsuario(idUsuarioReferencia) // Usamos esto para identificar el destinatario o el origen
                .mensaje(mensajeTexto)          // Todo el contenido va aquí
                .fechaCreacion(LocalDateTime.now())
                .build();
            
            producer.publish(event);
            log.info("Notificación enviada a Kafka: {}", mensajeTexto);

        } catch (Exception e) {
            log.error("Error al enviar notificación Kafka: {}", e.getMessage());
        }
    }
}