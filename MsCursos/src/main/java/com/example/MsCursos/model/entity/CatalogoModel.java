package com.example.mscursos.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
@Table(name = "catalogo")
public class CatalogoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo", nullable = false)
    private String categoria;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "idPadre", nullable = true)

    private Integer idPadre;
    @Builder.Default
    @Column(name = "estado", nullable = false)
    private Boolean estado = true;

    @Builder.Default
    @Embedded
    private AuditoriaModel auditoria = new AuditoriaModel();
}
