package unu.td.MsAcademico.model.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoridadResponse {
    private Integer id;
    private String idUsuario;
    private Integer idTipoAutoridad;
    private EntidadAcademicaResponse entidad;
    private Integer idTipoEntidad;
    private String tipoEntidad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
