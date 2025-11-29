package tdunu.MsSolicitudes.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "proceso_reingreso")
public class SolicitudesModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_Proceso")
  private Integer idProceso;

  @Column(name = "id_Estudiante")
  private Integer idEstudiante;

  @Column(name = "Proc_Codigo", length = 50)
  private String procCodigo;

  @Column(name = "Proc_Fecha_Inicio")
  private LocalDate procFechaInicio;

  @Column(name = "Proc_Fecha_Fin")
  private LocalDate procFechaFin;

  @Column(name = "Proc_Estado", length = 20)
  private String procEstado;

  @Column(name = "Proc_Observaciones", columnDefinition = "TEXT")
  private String procObservaciones;

  @Column(name = "Proc_Fecha_Pago_Ficha")
  private LocalDate procFechaPagoFicha;

  @Column(name = "Proc_Fecha_Emision_Ficha")
  private LocalDate procFechaEmisionFicha;

  @Column(name = "Proc_Fecha_Pago_Informe")
  private LocalDate procFechaPagoInforme;

  @Column(name = "Proc_Fecha_Solicitud_Informe")
  private LocalDate procFechaSolicitudInforme;

  @Column(name = "Proc_Fecha_Emision_Informe")
  private LocalDate procFechaEmisionInforme;

  @Column(name = "Proc_Fecha_Solicitud_Reingreso")
  private LocalDate procFechaSolicitudReingreso;

  @Column(name = "Proc_Fecha_Resolucion")
  private LocalDate procFechaResolucion;

  @Column(name = "Proc_Fecha_Matricula")
  private LocalDate procFechaMatricula;

  @Column(name = "Proc_Fase_Actual", length = 50)
  private String procFaseActual;

  @Column(name = "FECHA_CREACION")
  private LocalDateTime fechaCreacion;

  @Column(name = "USUARIO_CREACION", length = 50)
  private String usuarioCreacion;

  @Column(name = "FECHA_MODIFICACION")
  private LocalDateTime fechaModificacion;

  @Column(name = "USUARIO_MODIFICACION", length = 50)
  private String usuarioModificacion;

}