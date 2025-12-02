package tdunu.MsPosgrado.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "estudiante_proceso")
public class EstudianteProceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante_proceso") 
    private Long id;

    // --- Relaciones ---
    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "id_programa")
    private Long idPrograma;

    @Column(nullable = false)
    private String estadoGeneral;

    // --- Campos de Gestión Académica (MS1) ---
    @Column(name = "estado_proyecto")
    private String estadoProyecto; 

    @Column(name = "estado_informe_final")
    private String estadoInformeFinal; 

    @Column(name = "porcentaje_antiplagio")
    private Double porcentajeAntiplagio; 

    @Column(name = "listo_para_expedito")
    private Boolean listoParaExpedito; 

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_cierre_academico")
    private LocalDate fechaCierreAcademico; 
    
    // --- Auditoría y Ciclo de Vida ---
    @PrePersist
    public void prePersist() {
        this.fechaInicio = LocalDate.now();
        if (this.listoParaExpedito == null) {
            this.listoParaExpedito = false;
        }
    }
}