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
        return repository.findByActivoTrue();
    }

    @Override
    public Asesor obtenerPorId(Integer id) {
        Asesor asesor = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Asesor con ID {} no encontrado", id);
                    return new RuntimeException("Asesor no encontrado");
                });

        if (!asesor.getActivo()) {
            log.warn("Intento de acceso a Asesor inactivo (ID: {})", id);
            throw new RuntimeException("Asesor no encontrado");
        }

        return asesor;
    }

    @Override
    public Asesor actualizar(Integer id, Asesor data) {

        Asesor existente = obtenerPorId(id); // Trae los datos viejos completos

        // SOLUCIÓN: Preguntar antes de chancar

        // Si data trae nombres, actualizamos. Si es null, conservamos el viejo.
        if (data.getNombres() != null && !data.getNombres().isEmpty()) {
            existente.setNombres(data.getNombres());
        }

        if (data.getApellidos() != null && !data.getApellidos().isEmpty()) {
            existente.setApellidos(data.getApellidos());
        }

        if (data.getGradoMaximo() != null && !data.getGradoMaximo().isEmpty()) {
            existente.setGradoMaximo(data.getGradoMaximo());
        }

        if (data.getCvRuta() != null && !data.getCvRuta().isEmpty()) {
            existente.setCvRuta(data.getCvRuta());
        }

        if (data.getDeclaracionRuta() != null && !data.getDeclaracionRuta().isEmpty()) {
            existente.setDeclaracionRuta(data.getDeclaracionRuta());
        }

        // El estado no suele ser vacío, pero si es nulo no lo tocamos
        if (data.getEstadoAsesor() != null) {
            existente.setEstadoAsesor(data.getEstadoAsesor());
        }

        log.info("Actualizando asesor con ID {}", id);

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Asesor existente = obtenerPorId(id); // Lanza error si no existe

        log.warn("Realizando borrado LÓGICO (Auditoria) del asesor ID {}", id);

        // CAMBIO: Usamos el campo de la clase padre AuditoriaEntity
        existente.setActivo(false);

        // Guardamos el cambio. El registro persiste, pero queda marcado como inactivo.
        repository.save(existente);
    }

}
