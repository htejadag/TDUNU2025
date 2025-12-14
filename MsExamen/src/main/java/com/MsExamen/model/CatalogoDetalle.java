package com.MsExamen.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;
import java.time.LocalDateTime;

@Entity
@Table(name = "catalogo_detalle")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogoDetalle {

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        CatalogoDetalle that = (CatalogoDetalle) o;
        return getIdCatalogoDetalle() != null && Objects.equals(getIdCatalogoDetalle(), that.getIdCatalogoDetalle());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_catalogo_detalle")
    private Integer idCatalogoDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_catalogo", nullable = false)
    private Catalogo catalogo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "valor", length = 50, nullable = false)
    private String valor;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion", length = 50, updatable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion", length = 50)
    private String usuarioModificacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
        this.estado = true; // Default to active
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = LocalDateTime.now();
    }
}
