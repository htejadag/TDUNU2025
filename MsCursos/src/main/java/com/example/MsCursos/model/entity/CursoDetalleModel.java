package com.example.MsCursos.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cursoDetalle")


public class CursoDetalleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "idDocente")
    private Integer idDocente;

    @Column(name = "idCurso")
    private Integer idCurso;

    @Column(name = "idTipoCurso")
    private Integer idTipoCurso;

    @Column(name = "idSemestre")
    private Integer idSemestre;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "usuarioCreacion")
    private Integer usuarioCreacion;

    @Column(name = "usuarioModificacion")
    private Integer usuarioModificacion;

    @Column(name = "fechaCreacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;
}
