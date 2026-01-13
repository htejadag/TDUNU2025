package tdunu.MsPersona.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Persona")
public class PersonaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Persona")
    private Integer idPersona;

    @Column(name = "Per_Nombres", nullable = false, length = 100)
    private String perNombres;

    @Column(name = "Per_Apellidos", nullable = false, length = 100)
    private String perApellidos;

    @Column(name = "Per_Dni", unique = true, nullable = false, length = 8)
    private String perDni;

    @Column(name = "Per_Email", length = 100)
    private String perEmail;

    @Column(name = "Per_Telefono", length = 20)
    private String perTelefono;

    // Auditoría
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_MODIFICACION")
    private LocalDateTime fechaModificacion;

    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;
}
