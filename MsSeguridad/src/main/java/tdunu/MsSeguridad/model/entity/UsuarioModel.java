package tdunu.MsSeguridad.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long idUsuario;

    @Column(name = "codusuario", nullable = false, length = 50)
    private String codUsuario;

    @Column(name = "contrasena", nullable = false, length = 120)
    private String contrasena;

    @Column(nullable = false)
    private Integer estado = 1;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(length = 150)
    private String correo;

    @Column(length = 20)
    private String celular;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "idusuario"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "idrole")
    )
    private Set<RoleModel> roles = new HashSet<>();
}
