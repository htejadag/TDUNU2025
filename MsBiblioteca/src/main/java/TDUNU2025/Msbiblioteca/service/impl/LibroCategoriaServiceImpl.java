package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import TDUNU2025.Msbiblioteca.model.entity.LibroCategoria;
import TDUNU2025.Msbiblioteca.model.request.LibroCategoriaRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroCategoriaResponse;
import TDUNU2025.Msbiblioteca.repository.LibroCategoriaRepository;
import TDUNU2025.Msbiblioteca.service.LibroCategoriaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroCategoriaServiceImpl implements LibroCategoriaService {

    private final LibroCategoriaRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<LibroCategoriaResponse> listar() {
        return repo.findAll().stream()
                .map(item -> modelMapper.map(item, LibroCategoriaResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public LibroCategoriaResponse obtener(Long id) {
        LibroCategoria entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LibroCategoria", "id", id));
        return modelMapper.map(entity, LibroCategoriaResponse.class);
    }

    @Override
    public LibroCategoriaResponse registrar(LibroCategoriaRequest request) {
        if (repo.existsByLibro_IdLibroAndCategoriaLibro_IdCategoria(request.getIdLibro(), request.getIdCategoria())) {
            throw new RuntimeException("El libro ya está asignado a esta categoría");
        }
        LibroCategoria entity = modelMapper.map(request, LibroCategoria.class);
        LibroCategoria saved = repo.save(entity);
        return modelMapper.map(saved, LibroCategoriaResponse.class);
    }

    @Override
    public LibroCategoriaResponse actualizar(Long id, LibroCategoriaRequest request) {
        LibroCategoria entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LibroCategoria", "id", id));

        modelMapper.map(request, entity);
        entity.setIdLibroCategoria(id); // Asegurar ID
        
        LibroCategoria updated = repo.save(entity);
        return modelMapper.map(updated, LibroCategoriaResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("LibroCategoria", "id", id);
        }
        repo.deleteById(id);
    }
}