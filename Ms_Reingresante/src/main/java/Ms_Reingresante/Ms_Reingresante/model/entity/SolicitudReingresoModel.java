package Ms_Reingresante.Ms_Reingresante.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "solicitud_reingreso")
public class SolicitudReingresoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Solicitud")
    private Integer idSolicitud;

    @Column(name = "id_Proceso")
    private Integer idProceso;

    @Column(name = "Sol_Num_Registro")
    private String solNumRegistro;

    @Column(name = "Sol_Fecha_Presentacion")
    private LocalDate solFechaPresentacion;

    @Column(name = "Sol_Fecha_Respuesta")
    private LocalDate solFechaRespuesta;

    @Column(name = "Sol_Contenido", columnDefinition = "TEXT")
    private String solContenido;

    @Column(name = "Sol_Estado")
    private String solEstado;

    @Column(name = "Sol_Observaciones", columnDefinition = "TEXT")
    private String solObservaciones;

    @Column(name = "resolucion_id_Resolucion")
    private Integer resolucionIdResolucion;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_MODIFICACION")
    private LocalDateTime fechaModificacion;

    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;
}