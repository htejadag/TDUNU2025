package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Asesor;
import Postgrado.postgrado.Repository.AsesorRepository;
import Postgrado.postgrado.Service.AsesorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsesorServiceImpl implements AsesorService {

    private static final Logger log = LoggerFactory.getLogger(AsesorServiceImpl.class);

    private final AsesorRepository repository;

    public AsesorServiceImpl(AsesorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Asesor crear(Asesor asesor) {

        

        if (asesor.getEstadoAsesor() == null) {
            asesor.setEstadoAsesor("ACTIVO"); // Estado por defecto
        }

        log.info("Creando asesor: {}", asesor.getNombres());

        return repository.save(asesor);
    }

    @Override
    public List<Asesor> listar() {
        return repository.findAll();
    }

    @Override
    public Asesor obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Asesor con ID {} no encontrado", id);
                    return new RuntimeException("Asesor no encontrado");
                });
    }

    @Override
    public Asesor actualizar(Integer id, Asesor data) {

        Asesor existente = obtenerPorId(id); // Ya lanza excepción si no existe

        
        existente.setNombres(data.getNombres());
        existente.setApellidos(data.getApellidos());
        existente.setGradoMaximo(data.getGradoMaximo());
        existente.setCvRuta(data.getCvRuta());
        existente.setDeclaracionRuta(data.getDeclaracionRuta());
        existente.setEstadoAsesor(data.getEstadoAsesor());

        log.info("Actualizando asesor con ID {}", id);

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {

        Asesor existente = obtenerPorId(id); // Ya lanza excepción si no existe

        log.warn("Eliminando asesor con ID {}", id);

        repository.delete(existente);
    }

    

}
