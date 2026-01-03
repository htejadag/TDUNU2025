package unu.td.MsAcademico.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EntidadAcademicaResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaFundacion;
    private Boolean activo;
}
