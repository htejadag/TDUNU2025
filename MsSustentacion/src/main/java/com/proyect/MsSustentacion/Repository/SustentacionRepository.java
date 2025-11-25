package com.proyect.MsSustentacion.Repository;

import com.proyect.MsSustentacion.model.Entity.Sustentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SustentacionRepository extends JpaRepository<Sustentacion, Long> {
}