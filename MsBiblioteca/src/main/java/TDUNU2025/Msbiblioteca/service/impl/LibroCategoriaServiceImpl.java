package tdunu2025.msbiblioteca.service.impl;

import tdunu2025.msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.msbiblioteca.model.entity.LibroCategoria;
import tdunu2025.msbiblioteca.model.request.LibroCategoriaRequest;
import tdunu2025.msbiblioteca.model.response.LibroCategoriaResponse;
import tdunu2025.msbiblioteca.repository.LibroCategoriaRepository;
import tdunu2025.msbiblioteca.service.LibroCategoriaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroCategoriaServiceImpl implements LibroCategoriaService {

    private final LibroCategoriaRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<LibroCategoriaResponse> listar() {
        return repo.findAll().stream()
                .map(item -> modelMapper.map(item, LibroCategoriaResponse.class))
                .toList();
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