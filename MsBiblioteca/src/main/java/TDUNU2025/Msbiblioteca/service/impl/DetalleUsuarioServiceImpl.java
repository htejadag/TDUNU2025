package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import TDUNU2025.Msbiblioteca.repository.DetalleUsuarioRepository;
import TDUNU2025.Msbiblioteca.service.DetalleUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleUsuarioServiceImpl implements DetalleUsuarioService {

    @Autowired
    private DetalleUsuarioRepository repository;

    @Override
    public List<DetalleUsuario> listarTodo() {
        return repository.findAll();
    }

    @Override
    public Optional<DetalleUsuario> obtenerPorId(Integer idUsuario) {
        return repository.findById(idUsuario);
    }

    @Override
    public DetalleUsuario guardar(DetalleUsuario detalle) {
        return repository.save(detalle);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        repository.deleteById(idUsuario);
    }
}