package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Jurado;
import Postgrado.postgrado.Repository.JuradoRepository;
import Postgrado.postgrado.Service.JuradoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuradoServiceImpl implements JuradoService {

    private final JuradoRepository repository;

    public JuradoServiceImpl(JuradoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Jurado crear(Jurado jurado) {
        if (jurado.getEstadoJurado() == null) {
            jurado.setEstadoJurado("ACTIVO");
        }
        return repository.save(jurado);
    }

    @Override
    public List<Jurado> listar() {
        return repository.findByActivoTrue();
    }

    @Override
    public Jurado obtenerPorId(Integer id) {
        Jurado jurado = repository.findById(id).orElseThrow(() -> new RuntimeException("Jurado no encontrado"));

        if (!jurado.getActivo()) {
            throw new RuntimeException("Jurado no encontrado");
        }
        return jurado;
    }

    @Override
    public Jurado actualizar(Integer id, Jurado data) {
        Jurado existente = obtenerPorId(id);
        if (existente == null)
            return null;

        if (data.getNombres() != null && !data.getNombres().isEmpty()) {
            existente.setNombres(data.getNombres());
        }
        if (data.getApellidos() != null && !data.getApellidos().isEmpty()) {
            existente.setApellidos(data.getApellidos());
        }
        if (data.getEspecialidad() != null && !data.getEspecialidad().isEmpty()) {
            existente.setEspecialidad(data.getEspecialidad());
        }
        if (data.getEstadoJurado() != null) {
            existente.setEstadoJurado(data.getEstadoJurado());
        }

        return repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Jurado existente = obtenerPorId(id);
        if (existente != null) {
            existente.setActivo(false);
            repository.save(existente);
        }
    }
}
