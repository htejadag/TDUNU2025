package com.proyect.MsSustentacion.Repository;

import com.proyect.MsSustentacion.model.Entity.Sustentacion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SustentacionRepository extends JpaRepository<Sustentacion, Long> {
    boolean existsByActaNumero(String actaNumero);

    boolean existsByActaNumeroAndIdNot(String actaNumero, Long id);

    List<Sustentacion> findByEstadoResulIdNot(Short estadoId);

    Optional<Sustentacion> findByIdAndEstadoResulIdNot(Long id, Short estadoId);
}