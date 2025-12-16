package unu.td.MsAcademico.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class EntidadAcademicaBaseModel extends BaseModel {

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "text")
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaFundacion;
}
