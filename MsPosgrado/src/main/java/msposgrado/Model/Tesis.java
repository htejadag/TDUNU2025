package msposgrado.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tesis")
public class Tesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTesis;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    @JsonBackReference(value = "exp-tesis")
    private Expediente expediente;

    @OneToMany(mappedBy = "tesis")
    @JsonManagedReference(value = "tesis-revision")
    private List<Revision> revisiones;

    private String titulo;
    private String proyectoPdf;
    private String informeFinalPdf;
    private String antiplagioProyectoPdf;
    private String antiplagioFinalPdf;

    private String estadoProyecto;
    private String estadoInformeFinal;

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
