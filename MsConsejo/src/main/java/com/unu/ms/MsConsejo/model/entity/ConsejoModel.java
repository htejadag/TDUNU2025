package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "consejo")
@Data
@EqualsAndHashCode(callSuper = true)
public class ConsejoModel extends AuditoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consejo")
    private Integer idConsejo;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "id_estado")
    private Integer idEstado;

    @OneToMany(mappedBy = "consejo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MiembroConsejoModel> miembros;

    @OneToMany(mappedBy = "consejo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SesionConsejoModel> sesiones;

}