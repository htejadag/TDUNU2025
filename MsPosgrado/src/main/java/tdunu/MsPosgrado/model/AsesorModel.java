package tdunu.MsPosgrado.model;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "asesor")
public class AsesorModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asesor")
    private int id_asesor;

    @Column(name = "dni_asesor")
    private String dni_asesor;

    @Column(name = "nombre_asesor")
    private String nombre_asesor;

    @Column(name = "grado_asesor")
    private String grado_asesor;

    @Column(name = "cv_url_asesor")
    private String cv_url_asesor;

    @Column(name = "declaracion_jurada_asesor")
    private String declaracion_jurada_asesor;

    @Column(name = "fecha_resolucion_designacion")
    private LocalDate fecha_resolucion_designacion;

    @Column(name = "usuario_creacion_asesor")
    private String usuario_creacion_asesor;

    @Column(name = "fecha_creacion_asesor")
    private LocalDate fecha_creacion_asesor;

    @Column(name = "estado_asesor")
    private int estado_asesor;

    public int getId_asesor() {
        return id_asesor;
    }

    public void setId_asesor(int id_asesor) {
        this.id_asesor = id_asesor;
    }

    public String getDni_asesor() {
        return dni_asesor;
    }

    public void setDni_asesor(String dni_asesor) {
        this.dni_asesor = dni_asesor;
    }

    public String getNombre_asesor() {
        return nombre_asesor;
    }

    public void setNombre_asesor(String nombre_asesor) {
        this.nombre_asesor = nombre_asesor;
    }

    public String getGrado_asesor() {
        return grado_asesor;
    }

    public void setGrado_asesor(String grado_asesor) {
        this.grado_asesor = grado_asesor;
    }

    public String getCv_url_asesor() {
        return cv_url_asesor;
    }

    public void setCv_url_asesor(String cv_url_asesor) {
        this.cv_url_asesor = cv_url_asesor;
    }

    public String getDeclaracion_jurada_asesor() {
        return declaracion_jurada_asesor;
    }

    public void setDeclaracion_jurada_asesor(String declaracion_jurada_asesor) {
        this.declaracion_jurada_asesor = declaracion_jurada_asesor;
    }

    public LocalDate getFecha_resolucion_designacion() {
        return fecha_resolucion_designacion;
    }

    public void setFecha_resolucion_designacion(LocalDate fecha_resolucion_designacion) {
        this.fecha_resolucion_designacion = fecha_resolucion_designacion;
    }

    public String getUsuario_creacion_asesor() {
        return usuario_creacion_asesor;
    }

    public void setUsuario_creacion_asesor(String usuario_creacion_asesor) {
        this.usuario_creacion_asesor = usuario_creacion_asesor;
    }

    public LocalDate getFecha_creacion_asesor() {
        return fecha_creacion_asesor;
    }

    public void setFecha_creacion_asesor(LocalDate fecha_creacion_asesor) {
        this.fecha_creacion_asesor = fecha_creacion_asesor;
    }

    public int getEstado_asesor() {
        return estado_asesor;
    }

    public void setEstado_asesor(int estado_asesor) {
        this.estado_asesor = estado_asesor;
    }
}