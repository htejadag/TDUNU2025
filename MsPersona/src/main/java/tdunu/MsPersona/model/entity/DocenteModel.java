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
@Table(name = "Docente")
public class DocenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Docente")
    private Integer idDocente;

    // Datos personales
    @Column(name = "Doc_Nombres", nullable = false, length = 100)
    private String docNombres;

    @Column(name = "Doc_Apellidos", nullable = false, length = 100)
    private String docApellidos;

    @Column(name = "Doc_Dni", unique = true, nullable = false, length = 8)
    private String docDni;

    @Column(name = "Doc_Email", length = 100)
    private String docEmail;

    @Column(name = "Doc_Telefono", length = 20)
    private String docTelefono;

    // Campos específicos de docente
    @Column(name = "Doc_Codigo", unique = true, length = 10)
    private String docCodigo;

    @Column(name = "Doc_Facultad", length = 100)
    private String docFacultad;

    // Campos de auditoría
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_MODIFICACION")
    private LocalDateTime fechaModificacion;

    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;
}