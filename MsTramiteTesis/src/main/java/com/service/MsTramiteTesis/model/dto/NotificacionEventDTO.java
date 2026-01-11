package com.service.MsTramiteTesis.model.dto;

import java.time.LocalDateTime;

import com.service.MsTramiteTesis.model.enums.RolUsuario;
import com.service.MsTramiteTesis.model.enums.TipoEvento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionEventDTO {

    private TipoEvento tipoEvento;

    // Identificadores de usuarios involucrados
    private Integer idEstudiante;
    private Integer idAsesor;
    private Integer idCoordinador;

    // Identificadores de recursos
    private Long idProyecto;
    private Long idRevision;
    private Long idBorrador;

    // Destinatarios
    private RolUsuario rolDestinatario;

    // Mensaje de la notificación
    private String mensaje;

    // Metadatos adicionales
    private String titulo;
    private String detalles;

    // Timestamp del evento
    @Builder.Default
    private LocalDateTime fechaEvento = LocalDateTime.now();
}
