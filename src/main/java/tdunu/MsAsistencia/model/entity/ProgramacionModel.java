package tdunu.MsAsistencia.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "programacion")
public class ProgramacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sistema_origen", length = 50, nullable = false)
    private String sistemaOrigen;

    @Column(name = "tipo_programacion", length = 50, nullable = false)
    private String tipoProgramacion;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "contexto_id")
    private Integer contextoId;

    @Column(name = "tipo_contexto", length = 50)
    private String tipoContexto;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "total_sesiones")
    private Integer totalSesiones;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuarioCreacion", nullable = false)
    private Integer usuarioCreacion;

    @Column(name = "usuarioModificacion")
    private Integer usuarioModificacion;
}
