package com.unu.MsDocumentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documentos")
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private UUID id;

    @Column(name = "correlativo", length = 10)
    private String correlativo;

    @Column(name = "fechaEmision", nullable = false)
    private LocalDateTime fechaEmision;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "expedienteId", nullable = false, length = 36)
    private UUID expedienteId;

    @Column(name = "tipoDocumentoId", nullable = false)
    private Integer tipoDocumentoId;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "oficinaOrigenId", length = 36)
    private UUID oficinaOrigenId;

    @Column(name = "asunto", length = 255, nullable = false)
    private String asunto;

    @Column(name = "referencia", length = 100, nullable = false)
    private String referencia;
}