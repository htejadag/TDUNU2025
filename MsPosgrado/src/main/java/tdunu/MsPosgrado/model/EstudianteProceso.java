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
    private Long id;

    // Relación con el estudiante (guardamos el ID para simplificar)
    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    // Relación con el programa
    @Column(name = "id_programa")
    private Long idPrograma;

    // Estado general (Ej: EN_PROYECTO, EN_TESIS)
    @Column(nullable = false)
    private String estadoGeneral;

    // --- Campos específicos de tu MS (Gestión Académica) ---
    
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
    
    @PrePersist //  Auditoría y Ciclo de Vida (JPA Callbacks) 
    public void prePersist() {
        this.fechaInicio = LocalDate.now();
        if (this.listoParaExpedito == null) {
            this.listoParaExpedito = false;
        }
    }
}