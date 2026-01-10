package tdunu2025.msbiblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import tdunu2025.msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.msbiblioteca.model.entity.Libro;
import tdunu2025.msbiblioteca.model.request.LibroRequest;
import tdunu2025.msbiblioteca.model.response.LibroResponse;
import tdunu2025.msbiblioteca.repository.LibroRepository;
import tdunu2025.msbiblioteca.service.LibroService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<LibroResponse> listar() {
        return repo.findAll().stream()
                .map(libro -> modelMapper.map(libro, LibroResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public LibroResponse obtener(Long id) {
        Libro libro = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", id));
        return modelMapper.map(libro, LibroResponse.class);
    }

    @Override
    public LibroResponse registrar(LibroRequest request) {
        if (repo.existsByIsbn(request.getIsbn())) {
            throw new RuntimeException("El ISBN ya existe");
        }
        Libro entity = modelMapper.map(request, Libro.class);
        Libro saved = repo.save(entity);
        return modelMapper.map(saved, LibroResponse.class);
    }

    @Override
    public LibroResponse actualizar(Long id, LibroRequest request) {
        Libro libro = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", id));

        modelMapper.map(request, libro);
        libro.setIdLibro(id);
        
        Libro updated = repo.save(libro);
        return modelMapper.map(updated, LibroResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Libro", "id", id);
        }
        repo.deleteById(id);
    }
}