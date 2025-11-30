package com.proyect.MsSustentacion.Repository;

import com.proyect.MsSustentacion.model.Entity.Sustentacion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SustentacionRepository extends JpaRepository<Sustentacion, Long> {
    // Validar si existe acta al crear
    boolean existsByActaNumero(String actaNumero);

    // Validar si existe acta al editar (excluyendo el propio ID para no bloquearse
    // a sí mismo)
    boolean existsByActaNumeroAndIdNot(String actaNumero, Long id);

    // --- MÉTODOS DE BORRADO LÓGICO ---

    // 1. Listar: Trae todo lo que NO tenga el estado X (Eliminado)
    List<Sustentacion> findByEstadoResulIdNot(Short estadoId);

    // 2. Buscar por ID: Trae el registro si existe Y su estado NO es X (Eliminado)
    Optional<Sustentacion> findByIdAndEstadoResulIdNot(Long id, Short estadoId);
}