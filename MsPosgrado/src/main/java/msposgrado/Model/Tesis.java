package msposgrado.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tesis")
@Schema(description = "Entidad que representa una tesis académica dentro del proceso de posgrado")
public class Tesis extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la tesis", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idTesis;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    @JsonBackReference(value = "exp-tesis")
    @Schema(description = "Expediente al cual pertenece la tesis")
    private Expediente expediente;

    @OneToMany(mappedBy = "tesis")
    @JsonManagedReference(value = "tesis-revision")
    @Schema(description = "Lista de revisiones realizadas a la tesis")
    private List<Revision> revisiones;

    @Schema(description = "Título de la tesis", example = "Sistema de Gestión Académica para Posgrado")
    private String titulo;

    @Schema(description = "Ruta del archivo PDF del proyecto de tesis", example = "documentos/proyecto_tesis.pdf")
    private String proyectoPdf;

    @Schema(description = "Ruta del archivo PDF del informe final de la tesis", example = "documentos/informe_final.pdf")
    private String informeFinalPdf;

    @Schema(description = "Ruta del archivo PDF del reporte de antiplagio del proyecto", example = "documentos/antiplagio_proyecto.pdf")
    private String antiplagioProyectoPdf;

    @Schema(description = "Ruta del archivo PDF del reporte de antiplagio del informe final", example = "documentos/antiplagio_final.pdf")
    private String antiplagioFinalPdf;

    @Schema(description = "Estado actual del proyecto de tesis", example = "APROBADO")
    private String estadoProyecto;

    @Schema(description = "Estado actual del informe final de la tesis", example = "EN_REVISIÓN")
    private String estadoInformeFinal;

    @Schema(description = "Fecha y hora de registro de la tesis", example = "2025-01-15T10:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public Integer getIdTesis() {
        return idTesis;
    }

    public void setIdTesis(Integer idTesis) {
        this.idTesis = idTesis;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public List<Revision> getRevisiones() {
        return revisiones;
    }

    public void setRevisiones(List<Revision> revisiones) {
        this.revisiones = revisiones;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProyectoPdf() {
        return proyectoPdf;
    }

    public void setProyectoPdf(String proyectoPdf) {
        this.proyectoPdf = proyectoPdf;
    }

    public String getInformeFinalPdf() {
        return informeFinalPdf;
    }

    public void setInformeFinalPdf(String informeFinalPdf) {
        this.informeFinalPdf = informeFinalPdf;
    }

    public String getAntiplagioProyectoPdf() {
        return antiplagioProyectoPdf;
    }

    public void setAntiplagioProyectoPdf(String antiplagioProyectoPdf) {
        this.antiplagioProyectoPdf = antiplagioProyectoPdf;
    }

    public String getAntiplagioFinalPdf() {
        return antiplagioFinalPdf;
    }

    public void setAntiplagioFinalPdf(String antiplagioFinalPdf) {
        this.antiplagioFinalPdf = antiplagioFinalPdf;
    }

    public String getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(String estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
    }

    public String getEstadoInformeFinal() {
        return estadoInformeFinal;
    }

    public void setEstadoInformeFinal(String estadoInformeFinal) {
        this.estadoInformeFinal = estadoInformeFinal;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}