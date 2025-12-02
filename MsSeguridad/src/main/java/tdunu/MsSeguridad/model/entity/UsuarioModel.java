package tdunu.MsSeguridad.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long idUsuario;

    @Column(name = "codusuario", nullable = false, length = 50)
    private String codUsuario; // código único del usuario: alu123, doc45, admin1, etc.

    @Column(name = "contrasena", nullable = false, length = 120)
    private String contrasena;

    @Column(nullable = false)
    private Integer estado = 1; // 1 activo, 0 desactivado
}
