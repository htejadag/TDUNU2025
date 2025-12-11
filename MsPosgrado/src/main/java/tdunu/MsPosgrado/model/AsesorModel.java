package tdunu.MsPosgrado.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@Entity
@Table(name = "asesor")
@EntityListeners(AuditingEntityListener.class)

public class AsesorModel implements Serializable {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asesor")
    private int idAsesor;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "grado_maximo", nullable = false)
    private String gradoMaximo;

    @Column(name = "cv_ruta")
    private String cvRuta;

    @Column(name = "declaracion_ruta")
    private String declaracionRuta;

    @Column(name = "estado_asesor")
    private String estadoAsesor;

    // Auditoría
    @CreatedDate
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @LastModifiedDate
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @CreatedBy
    @Column(name = "creado_por",updatable = false)
    private String creadoPor;

    @LastModifiedBy
    @Column(name = "modificado_por")
    private String modificadoPor;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    // ======== GETTERS & SETTERS ========

    public int getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(int idAsesor) {
        this.idAsesor = idAsesor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGradoMaximo() {
        return gradoMaximo;
    }

    public void setGradoMaximo(String gradoMaximo) {
        this.gradoMaximo = gradoMaximo;
    }

    public String getCvRuta() {
        return cvRuta;
    }

    public void setCvRuta(String cvRuta) {
        this.cvRuta = cvRuta;
    }

    public String getDeclaracionRuta() {
        return declaracionRuta;
    }

    public void setDeclaracionRuta(String declaracionRuta) {
        this.declaracionRuta = declaracionRuta;
    }

    public String getEstadoAsesor() {
        return estadoAsesor;
    }

    public void setEstadoAsesor(String estadoAsesor) {
        this.estadoAsesor = estadoAsesor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
