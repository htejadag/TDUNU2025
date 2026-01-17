package com.example.mscursos.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)

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

    @Builder.Default
    @Column(name = "estado", nullable = false)
    private Boolean estado = true;

    @Builder.Default
    @Embedded
    private AuditoriaModel auditoria = new AuditoriaModel();
}
