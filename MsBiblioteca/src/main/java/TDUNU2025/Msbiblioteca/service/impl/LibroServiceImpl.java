package TDUNU2025.Msbiblioteca.service.impl;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Libro;
import com.unu.TDUNU2025.Msbiblioteca.repository.LibroRepository;
import com.unu.TDUNU2025.Msbiblioteca.service.LibroService;

import TDUNU2025.Msbiblioteca.model.request.LibroRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<LibroResponse> listar() {
        return repo.findAll()
                .stream()
                .map(libro -> modelMapper.map(libro, LibroResponse.class))
                .toList(); // Java 17+
    }

    @Override
    public LibroResponse obtener(Long id) {
        Libro libro = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        return modelMapper.map(libro, LibroResponse.class);
    }

    @Override
    public LibroResponse registrar(LibroRequest request) {

        if (repo.existsByIsbn(request.getIsbn())) {
            throw new RuntimeException("El ISBN ya existe");
        }

        // Request -> Entity
        Libro entity = modelMapper.map(request, Libro.class);

        // Guardar
        Libro saved = repo.save(entity);

        // Entity -> Response
        return modelMapper.map(saved, LibroResponse.class);
    }

    @Override
    public LibroResponse actualizar(Long id, LibroRequest request) {

        Libro libro = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // Actualiza SOLO los campos del request
        modelMapper.map(request, libro);

        Libro updated = repo.save(libro);

        return modelMapper.map(updated, LibroResponse.class);
    }

    @Override
    public void eliminar(Long id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("El libro no existe");
        }

        repo.deleteById(id);
    }
}
