package tdunu.MsPersona.model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Estudiante") // O "ESTUDIANTE" si prefieres mayúsculas
public class EstudianteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Estudiante")
    private Integer idEstudiante;

    // Datos personales
    @Column(name = "Est_Nombres", nullable = false, length = 100)
    private String estNombres;

    @Column(name = "Est_Apellidos", nullable = false, length = 100)
    private String estApellidos;

    @Column(name = "Est_Dni", unique = true, nullable = false, length = 8)
    private String estDni;

    @Column(name = "Est_Email", length = 100)
    private String estEmail;

    @Column(name = "Est_Telefono", length = 20)
    private String estTelefono;

    // Campos específicos de estudiante
    @Column(name = "Est_Codigo", unique = true, length = 10)
    private String estCodigo;

    @Column(name = "Est_Facultad", length = 100)
    private String estFacultad;

    @Column(name = "Est_Escuela_Profesional", length = 100)
    private String estEscuelaProfesional;

    @Column(name = "Est_Ciclo_Relativo", length = 20)
    private String estCicloRelativo;

    // Campos de auditoría (si los necesitas)
    @Column(name = "FECHA_CREACION")
    private java.time.LocalDateTime fechaCreacion;

    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_MODIFICACION")
    private java.time.LocalDateTime fechaModificacion;

    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;
}