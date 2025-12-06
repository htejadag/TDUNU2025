package com.MsExamen.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "pregunta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private Integer idPregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_examen", nullable = false)
    private Examen examen;

    @Column(name = "pregunta", nullable = false, columnDefinition = "TEXT")
    private String preguntaTexto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", columnDefinition = "VARCHAR(20) DEFAULT 'opcion_multiple'")
    private TipoPregunta tipo;

    public enum TipoPregunta {
        opcion_multiple,
        respuesta_corta
    }

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion", length = 50, updatable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion", length = 50)
    private String usuarioModificacion;

    @PrePersist
    public void prePersist() {
        if (this.tipo == null) {
            this.tipo = TipoPregunta.opcion_multiple;
        }
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = LocalDateTime.now();
    }
}
