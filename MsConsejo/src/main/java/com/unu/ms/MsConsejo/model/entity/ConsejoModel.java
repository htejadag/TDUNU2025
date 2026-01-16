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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * Entidad que representa un Consejo en el sistema.
 * 
 * Un consejo es una estructura organizacional compuesta por miembros que participan
 * en sesiones periódicas. Cada consejo puede tener múltiples miembros y sesiones asociadas.
 * 
 * Propiedades principales:
 * - Nombre y descripción del consejo
 * - Estado actual del consejo (ACTIVO, INACTIVO, etc.)
 * - Fecha de creación (automáticamente registrada)
 * - Relaciones con miembros del consejo y sesiones
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Entity
@Table(name = "consejo")
@Data
public class ConsejoModel {

    /** Identificador único del consejo */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consejo")
    private Integer idConsejo;

    /** Nombre del consejo (máximo 150 caracteres, requerido) */
    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    /** Descripción detallada del consejo */
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    /** ID del estado del consejo (referencia al catálogo de estados) */
    @Column(name = "id_estado")
    private Integer idEstado;

    /** Fecha y hora de creación del consejo (se establece automáticamente) */
    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    /** Lista de miembros que pertenecen a este consejo */
    @OneToMany(mappedBy = "consejo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MiembroConsejoModel> miembros;

    /** Lista de sesiones asociadas a este consejo */
    @OneToMany(mappedBy = "consejo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SesionConsejoModel> sesiones;

}