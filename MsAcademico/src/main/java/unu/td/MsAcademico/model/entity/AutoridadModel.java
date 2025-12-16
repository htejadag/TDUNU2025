package unu.td.MsAcademico.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "autoridades")
@EntityListeners(AuditingEntityListener.class)
public class AutoridadModel extends BaseModel{

    @Column(nullable = false)
    private String idUsuario;

    @ManyToOne()
    @JoinColumn(name = "idTipoAutoridad", nullable = false)
    private CatalogoModel tipoAutoridad;

    @ManyToOne()
    @JoinColumn(name = "idTipoEntidad", nullable = false)
    private CatalogoModel tipoEntidad;

    @Column(nullable = false)
    private Integer idEntidad;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    private LocalDate fechaFin;
}
