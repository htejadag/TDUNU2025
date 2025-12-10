package Ms_Reingresante.Ms_Reingresante.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class InformeAcademicoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Informe")
    private Long idInforme; // id Informe INT [cite: 116]

    @Column(name = "id_Proceso")
    private Integer idProceso; // id Proceso INT (Clave Foránea) [cite: 117]

    @Column(name = "Inf_Numero", length = 50)
    private String infNumero; // Inf Numero VARCHAR(50) [cite: 118]

    @Column(name = "Inf_TUPA", length = 20)
    private String infTupa; // Inf TUPA VARCHAR(20) [cite: 119]

    @Column(name = "Inf_Num_Registro", length = 20)
    private String infNumRegistro; // Inf Num Registro VARCHAR(20) [cite: 120]

    @Column(name = "Inf_Hora_Recepcion")
    private LocalTime horaRecepcion; // Inf Hora Recepcion TIME [cite: 121]

    @Column(name = "Inf_Fecha_Solicitud")
    private LocalDate fechaSolicitud; // Inf Fecha Solicitud DATE [cite: 122]

    @Column(name = "Inf_Fecha_Emision")
    private LocalDate fechaEmision; // Inf Fecha Emision DATE [cite: 123]

    @Column(name = "Inf_Emitido_Por", length = 50)
    private String emitidoPor; // Inf Emitido Por VARCHAR(50) [cite: 124]

    // Campos de Auditoría
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion; // FECHA CREACION DATETIME [cite: 125]

    @Column(name = "USUARIO_CREACION", length = 50)
    private String usuarioCreacion; // USUARIO CREACION VARCHAR(50) [cite: 126]

    @Column(name = "FECHA_MODIFICACION")
    private LocalDateTime fechaModificacion; // FECHA MODIFICACION DATETIME [cite: 127]

    @Column(name = "USUARIO_MODIFICACION", length = 5) // Se ajusta el length a 50 si es necesario
    private String usuarioModificacion; // USUARIO MODIFICACION VARCHAR(5) [cite: 128]
}