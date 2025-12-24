package unu.td.msacademico.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoridadResponse {
    private Integer id;
    private String idUsuario;
    private CatalogoResponse tipoAutoridad;
    private CatalogoResponse tipoEntidad;
    private EntidadAcademicaResponse entidad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean activo;
}
