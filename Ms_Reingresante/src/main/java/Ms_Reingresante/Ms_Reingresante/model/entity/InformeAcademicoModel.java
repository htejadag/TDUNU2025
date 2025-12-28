package Ms_Reingresante.Ms_Reingresante.model.entity;

import java.time.LocalDate;
import java.time.LocalTime; // Para el campo TIME

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "informe_academico")
public class InformeAcademicoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Informe")
    private Long idInforme;

    @Column(name = "id_Proceso")
    private Integer idProceso;

    @Column(name = "Inf_Numero")
    private String infNumero;

    @Column(name = "Inf_TUPA")
    private String infTupa;

    @Column(name = "Inf_Num_Registro")
    private String infNumRegistro;

    // ✅ TIME
    @Column(name = "Inf_Hora_Recepcion")
    private LocalTime horaRecepcion;

    // ✅ DATE
    @Column(name = "Inf_Fecha_Solicitud")
    private LocalDate fechaSolicitud;

    // ✅ DATE
    @Column(name = "Inf_Fecha_Emision")
    private LocalDate fechaEmision;

    @Column(name = "Inf_Emitido_Por")
    private String emitidoPor;

    // ✅ DATETIME
    @Column(name = "FECHA_CREACION")
    private LocalDate fechaCreacion;

    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_MODIFICACION")
    private LocalDate fechaModificacion;

    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;

    }