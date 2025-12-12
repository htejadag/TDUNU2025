package unu.td.msacademico.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
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

    @Column(nullable = false)
    private Integer idTipoAutoridad;

    @Column(nullable = false)
    private Integer idTipoEntidad;

    @Column(nullable = false)
    private Integer idEntidad;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;
}
