package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.config.BusinessException; 
import TDUNU2025.Msbiblioteca.model.entity.LibroAutor;
import TDUNU2025.Msbiblioteca.model.request.LibroAutorRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroAutorResponse;
import TDUNU2025.Msbiblioteca.repository.AutorRepository; 
import TDUNU2025.Msbiblioteca.repository.LibroAutorRepository;
import TDUNU2025.Msbiblioteca.repository.LibroRepository; 
import TDUNU2025.Msbiblioteca.service.LibroAutorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroAutorServiceImpl implements LibroAutorService {

    private final LibroAutorRepository repo;
    private final LibroRepository libroRepository; 
    private final AutorRepository autorRepository; 
    private final ModelMapper modelMapper;

    // Mensajes constantes
    private static final String MSG_NOT_FOUND = "La asignación Libro-Autor no existe";
    private static final String MSG_DUPLICATE = "Este autor ya está asignado a este libro";
    private static final String MSG_REQ_LIBRO = "El ID del libro es obligatorio";
    private static final String MSG_REQ_AUTOR = "El ID del autor es obligatorio";

    @Override
    @Transactional(readOnly = true)
    public List<LibroAutorResponse> listar() {
        return repo.findAll().stream()
                .map(entity -> modelMapper.map(entity, LibroAutorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LibroAutorResponse obtener(Long id) {
        LibroAutor entity = repo.findById(id)
                .orElseThrow(() -> new BusinessException(MSG_NOT_FOUND));
        return modelMapper.map(entity, LibroAutorResponse.class);
    }

    @Override
    @Transactional
    public LibroAutorResponse registrar(LibroAutorRequest request) {

        validarRequest(request);

        if (!libroRepository.existsById(request.getIdLibro())) {
            throw new BusinessException("El Libro con ID " + request.getIdLibro() + " no existe.");
        }

        if (!autorRepository.existsById(request.getIdAutor())) {
            throw new BusinessException("El Autor con ID " + request.getIdAutor() + " no existe.");
        }

        if (repo.existsByIdLibroAndIdAutor(request.getIdLibro(), request.getIdAutor())) {
            throw new BusinessException(MSG_DUPLICATE);
        }

        LibroAutor entity = modelMapper.map(request, LibroAutor.class);
        LibroAutor saved = repo.save(entity);
        log.info("Relación guardada: Libro {} - Autor {}", saved.getIdLibro(), saved.getIdAutor());

        return modelMapper.map(saved, LibroAutorResponse.class);
    }

    @Override
    @Transactional
    public LibroAutorResponse actualizar(Long id, LibroAutorRequest request) {
        LibroAutor entity = repo.findById(id)
                .orElseThrow(() -> new BusinessException(MSG_NOT_FOUND));

        validarRequest(request);

        if (!entity.getIdLibro().equals(request.getIdLibro()) && !libroRepository.existsById(request.getIdLibro())) {
                throw new BusinessException("El Libro con ID " + request.getIdLibro() + " no existe.");
            }
        

        if (!entity.getIdAutor().equals(request.getIdAutor()) && !autorRepository.existsById(request.getIdAutor())) {
                throw new BusinessException("El Autor con ID " + request.getIdAutor() + " no existe.");
            }
        

        boolean idsCambiaron = !entity.getIdLibro().equals(request.getIdLibro()) || 
                               !entity.getIdAutor().equals(request.getIdAutor());

        if (idsCambiaron && repo.existsByIdLibroAndIdAutor(request.getIdLibro(), request.getIdAutor())) {
             throw new BusinessException(MSG_DUPLICATE);
        }

        modelMapper.map(request, entity);
        entity.setIdLibroAutor(id); 

        LibroAutor updated = repo.save(entity);
        return modelMapper.map(updated, LibroAutorResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new BusinessException(MSG_NOT_FOUND);
        }
        repo.deleteById(id);
        log.warn("Relación Libro-Autor eliminada ID: {}", id);
    }

    private void validarRequest(LibroAutorRequest request) {
        if (request.getIdLibro() == null) {
            throw new BusinessException(MSG_REQ_LIBRO);
        }
        if (request.getIdAutor() == null) {
            throw new BusinessException(MSG_REQ_AUTOR);
        }
    }
}