package tdunu.MsAsistencia.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AsistenciaResponse {

    private Integer id;

    private String sistemaOrigen;

    private String tipoEntidad;

    private Integer entidadId;

    private String tipoEvento;

    private Integer eventoId;

    private LocalDateTime fechaHora;

    private String estado;

    private String observaciones;

    private LocalDateTime fechaRegistro;

    private String usuarioRegistro;
}
