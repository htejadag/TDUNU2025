package com.proyect.MsSustentacion.Service.impl;

import com.proyect.MsSustentacion.model.Entity.Sustentacion;
import com.proyect.MsSustentacion.Repository.SustentacionRepository;
import com.proyect.MsSustentacion.Service.SustentacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SustentacionServiceImpl implements SustentacionService {

    private final SustentacionRepository repository;

    public SustentacionServiceImpl(SustentacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Sustentacion> findAll() {
        return repository.findAll();
    }

    @Override
    public Sustentacion findById(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Sustentacion save(Sustentacion sustentacion) {
        return repository.save(sustentacion);
    }

    @Override
    public Sustentacion update(Long id, Sustentacion s) {
        Sustentacion db = findById(id);
        if (db == null)
            return null;

        db.setTramiteId(s.getTramiteId());
        db.setFecha(s.getFecha());
        db.setHora(s.getHora());
        db.setLugar(s.getLugar());
        db.setEstadoResulId(s.getEstadoResulId());
        db.setActaNumero(s.getActaNumero());
        db.setUsuarioModificacion(s.getUsuarioModificacion());

        return repository.save(db);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
