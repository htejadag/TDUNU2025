package com.proyect.MsSustentacion.Service;

import com.proyect.MsSustentacion.model.Entity.Sustentacion;
import java.util.List;

public interface SustentacionService {

    List<Sustentacion> findAll();

    Sustentacion findById(Long id);

    Sustentacion save(Sustentacion sustentacion);

    Sustentacion update(Long id, Sustentacion sustentacion);

    void delete(Long id);
}
