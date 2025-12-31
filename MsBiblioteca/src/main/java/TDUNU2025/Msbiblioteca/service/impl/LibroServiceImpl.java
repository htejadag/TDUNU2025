package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.Libro;
import TDUNU2025.Msbiblioteca.model.request.LibroRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroResponse;
import TDUNU2025.Msbiblioteca.repository.LibroRepository;
import TDUNU2025.Msbiblioteca.service.LibroService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LibroResponse> listar() {
        return repo.findAll()
                .stream()
                .map(libro -> modelMapper.map(libro, LibroResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LibroResponse obtener(Long id) {
        Libro libro = repo.findById(id)
                .orElseThrow(() -> new BusinessException("Libro no encontrado con ID: " + id));

        return modelMapper.map(libro, LibroResponse.class);
    }

    @Override
    @Transactional
    public LibroResponse registrar(LibroRequest request) {

        validarDatosLibro(request);

        if (repo.existsByIsbn(request.getIsbn())) {
            throw new BusinessException("El ISBN " + request.getIsbn() + " ya se encuentra registrado");
        }

        Libro entity = modelMapper.map(request, Libro.class);
        Libro saved = repo.save(entity);
        
        log.info("Libro registrado con éxito: {}", saved.getTitulo());

        return modelMapper.map(saved, LibroResponse.class);
    }

    @Override
    @Transactional
    public LibroResponse actualizar(Long id, LibroRequest request) {
        Libro libro = repo.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Libro no encontrado"));

        validarDatosLibro(request);

        if (!libro.getIsbn().equals(request.getIsbn()) && repo.existsByIsbn(request.getIsbn())) {
             throw new BusinessException("El nuevo ISBN ya pertenece a otro libro registrado");
        }

        modelMapper.map(request, libro);
        
        libro.setIdLibro(id);

        Libro updated = repo.save(libro);
        log.info("Libro actualizado ID: {}", id);

        return modelMapper.map(updated, LibroResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new BusinessException("El libro no existe, no se puede eliminar");
        }
        repo.deleteById(id);
        log.warn("Libro eliminado con ID: {}", id);
    }


    private void validarDatosLibro(LibroRequest request) {
        if (request.getIsbn() == null || request.getIsbn().trim().isEmpty()) {
            throw new BusinessException("El ISBN es obligatorio");
        }
        if (request.getTitulo() == null || request.getTitulo().trim().isEmpty()) {
            throw new BusinessException("El título del libro es obligatorio");
        }
        if (request.getIdEditorial() == null || request.getIdEditorial() <= 0) {
            throw new BusinessException("Debe seleccionar una editorial válida");
        }
        if (request.getIdEstadoLibro() == null) {
            throw new BusinessException("Debe seleccionar el estado del libro");
        }
        if (request.getNumeroPaginas() != null && request.getNumeroPaginas() <= 0) {
            throw new BusinessException("El número de páginas debe ser mayor a 0");
        }
    }
}