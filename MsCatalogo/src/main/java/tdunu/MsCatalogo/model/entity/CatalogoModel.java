package tdunu.MsCatalogo.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "catalogo")
public class CatalogoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo_catalogo_id", nullable = false)
    private Integer tipoCatalogoId;

    @Column(name = "codigo", length = 50, nullable = false)
    private String codigo;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "valor", length = 100)
    private String valor;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_creacion", nullable = false)
    private Integer usuarioCreacion;

    @Column(name = "usuario_modificacion")
    private Integer usuarioModificacion;

    // Relación con TipoCatalogo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_catalogo_id", insertable = false, updatable = false)
    private TipoCatalogoModel tipoCatalogo;
}
