package unu.td.MsAcademico.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "facultad")
public class FacultadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado;

    @Column(name = "usuarioCreacion", nullable = false, updatable = false)
    private Integer usuarioCreacion;

    @Column(name = "usuarioModificacion", nullable = false)
    private Integer usuarioModificacion;

    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fechaModificacion", nullable = false)
    private LocalDate fechaModificacion;
}
