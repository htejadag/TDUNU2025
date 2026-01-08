package msposgrado.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;

@Entity
@Table(name = "expediente_jurado")
public class ExpedienteJurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExpJurado;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    @JsonBackReference(value = "exp-jurado")
    private Expediente expediente;

    @ManyToOne
    @JoinColumn(name = "id_jurado", nullable = false)
    private Jurado jurado;

    private String rol;

    private LocalDateTime fechaDesignacion = LocalDateTime.now();

    public Integer getIdExpJurado() {
        return idExpJurado;
    }

    public void setIdExpJurado(Integer idExpJurado) {
        this.idExpJurado = idExpJurado;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Jurado getJurado() {
        return jurado;
    }

    public void setJurado(Jurado jurado) {
        this.jurado = jurado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaDesignacion() {
        return fechaDesignacion;
    }

    public void setFechaDesignacion(LocalDateTime fechaDesignacion) {
        this.fechaDesignacion = fechaDesignacion;
    }
}
