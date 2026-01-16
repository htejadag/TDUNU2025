package tdunu.MsAsistencia.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AsistenciaRequest {

    private String sistemaOrigen;

    private String tipoEntidad;

    private Integer entidadId;

    private String tipoEvento;

    private Integer eventoId;

    private LocalDateTime fechaHora;

    private String estado;

    private String observaciones;

    private String usuarioRegistro;
}
