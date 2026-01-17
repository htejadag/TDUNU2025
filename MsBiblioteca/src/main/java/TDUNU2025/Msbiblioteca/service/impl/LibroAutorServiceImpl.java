package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.model.entity.LibroAutor;
import TDUNU2025.Msbiblioteca.model.request.LibroAutorRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroAutorResponse;
import TDUNU2025.Msbiblioteca.repository.LibroAutorRepository;
import TDUNU2025.Msbiblioteca.service.LibroAutorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroAutorServiceImpl implements LibroAutorService {

    private final LibroAutorRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<LibroAutorResponse> listar() {
        return repo.findAll()
                .stream()
                .map(libroAutor -> modelMapper.map(libroAutor, LibroAutorResponse.class))
                .toList(); // Java 17+
    }

    @Override
    public LibroAutorResponse obtener(Long id) {
        LibroAutor libroAutor = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación Libro-Autor no encontrada"));

        return modelMapper.map(libroAutor, LibroAutorResponse.class);
    }

    @Override
    public LibroAutorResponse registrar(LibroAutorRequest request) {

        if (repo.existsByIdLibroAndIdAutorAndRol(
                request.getIdLibro(),
                request.getIdAutor(),
                request.getRol()
        )) {
            throw new RuntimeException("Este autor ya está registrado con ese rol en este libro");
        }

        // Request -> Entity
        LibroAutor entity = modelMapper.map(request, LibroAutor.class);

        // Guardar
        LibroAutor saved = repo.save(entity);

        // Entity -> Response
        return modelMapper.map(saved, LibroAutorResponse.class);
    }

    @Override
    public LibroAutorResponse actualizar(Long id, LibroAutorRequest request) {

        LibroAutor libroAutor = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación Libro-Autor no encontrada"));

        // Actualiza SOLO los datos del request
        modelMapper.map(request, libroAutor);

        LibroAutor updated = repo.save(libroAutor);

        return modelMapper.map(updated, LibroAutorResponse.class);
    }

    @Override
    public void eliminar(Long id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("La relación Libro-Autor no existe");
        }

        repo.deleteById(id);
    }
}
