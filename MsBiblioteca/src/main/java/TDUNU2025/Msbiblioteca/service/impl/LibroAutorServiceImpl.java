package tdunu2025.Msbiblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import tdunu2025.Msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.Msbiblioteca.model.entity.LibroAutor;
import tdunu2025.Msbiblioteca.model.request.LibroAutorRequest;
import tdunu2025.Msbiblioteca.model.response.LibroAutorResponse;
import tdunu2025.Msbiblioteca.repository.LibroAutorRepository;
import tdunu2025.Msbiblioteca.service.LibroAutorService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroAutorServiceImpl implements LibroAutorService {

    private final LibroAutorRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<LibroAutorResponse> listar() {
        return repo.findAll().stream()
                .map(la -> modelMapper.map(la, LibroAutorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public LibroAutorResponse obtener(Long id) {
        LibroAutor la = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LibroAutor", "id", id));
        return modelMapper.map(la, LibroAutorResponse.class);
    }

    @Override
    public LibroAutorResponse registrar(LibroAutorRequest request) {
        if (repo.existsByLibro_IdLibroAndAutor_IdAutorAndRol(
                request.getIdLibro(), request.getIdAutor(), request.getRol())) {
            throw new RuntimeException("Este autor ya está registrado con ese rol en este libro");
        }
        LibroAutor entity = modelMapper.map(request, LibroAutor.class);
        LibroAutor saved = repo.save(entity);
        return modelMapper.map(saved, LibroAutorResponse.class);
    }

    @Override
    public LibroAutorResponse actualizar(Long id, LibroAutorRequest request) {
        LibroAutor existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LibroAutor", "id", id));
        
        modelMapper.map(request, existing);
        existing.setIdLibroAutor(id);
        
        LibroAutor updated = repo.save(existing);
        return modelMapper.map(updated, LibroAutorResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("LibroAutor", "id", id);
        }
        repo.deleteById(id);
    }
}