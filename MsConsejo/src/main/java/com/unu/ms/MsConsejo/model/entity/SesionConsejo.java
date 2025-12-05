package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sesion_consejo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SesionConsejo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Integer idSesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consejo", nullable = false)
    private Consejo consejo;

    @Column(name = "numero_sesion", length = 50)
    private String numeroSesion;

    @Column(name = "nombre_sesion", length = 255)
    private String nombreSesion;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_sesion", nullable = false)
    private LocalDate fechaSesion;

    @Column(name = "id_tipo_sesion")
    private Integer idTipoSesion;

    @Column(name = "id_usuario_registro")
    private Integer idUsuarioRegistro;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @OneToMany(mappedBy = "sesionConsejo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AsistenciaConsejero> asistencias;

    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
    }
}
