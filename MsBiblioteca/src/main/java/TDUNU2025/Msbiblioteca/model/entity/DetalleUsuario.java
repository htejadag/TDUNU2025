package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalle_usuario")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_detalle_usuario")
    private Integer idDetalleUsuario;

    @Column(name = "id_usuario", nullable = false, unique = true)
    private Integer idUsuario;

    @Column(name = "total_prestamos")
    private Integer totalPrestamos;

    @Column(name = "total_multas")
    private Integer totalMultas;

    @Column(name = "fecha_ultimo_prestamo")
    private LocalDate fechaUltimoPrestamo;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;


    @PrePersist
    public void prePersist() {
        this.fechaActualizacion = LocalDateTime.now();

        if (this.totalPrestamos == null) this.totalPrestamos = 0;
        if (this.totalMultas == null) this.totalMultas = 0;
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}