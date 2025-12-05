package com.unu.ms.MsGradosTitulos.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "seguimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private Integer idSeguimiento;

    @Column(name = "id_entidad_catalogo")
    private Integer idEntidadCatalogo;

    @Column(name = "entidad_id")
    private Integer entidadId;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "comentario", columnDefinition = "NVARCHAR(MAX)")
    private String comentario;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }
}
