package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.config.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.LibroCategoria;
import TDUNU2025.Msbiblioteca.model.request.LibroCategoriaRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroCategoriaResponse;
import TDUNU2025.Msbiblioteca.repository.CategoriaRepository; 
import TDUNU2025.Msbiblioteca.repository.LibroCategoriaRepository;
import TDUNU2025.Msbiblioteca.repository.LibroRepository;      
import TDUNU2025.Msbiblioteca.service.LibroCategoriaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroCategoriaServiceImpl implements LibroCategoriaService {

    private final LibroCategoriaRepository repo;
    private final LibroRepository libroRepository;         // Inyección para validar
    private final CategoriaRepository categoriaRepository; // Inyección para validar
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LibroCategoriaResponse> listar() {
        return repo.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, LibroCategoriaResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LibroCategoriaResponse obtener(Long id) {
        LibroCategoria entity = repo.findById(id)
                .orElseThrow(() -> new BusinessException("Relación libro-categoría no encontrada con ID: " + id));

        return modelMapper.map(entity, LibroCategoriaResponse.class);
    }

    @Override
    @Transactional
    public LibroCategoriaResponse registrar(LibroCategoriaRequest request) {
        // 1. Validar integridad básica
        if (request.getIdLibro() == null || request.getIdCategoria() == null) {
            throw new BusinessException("Debe indicar el Libro y la Categoría");
        }

        // 2. Validar existencia del Libro
        if (!libroRepository.existsById(request.getIdLibro())) {
            throw new BusinessException("El Libro con ID " + request.getIdLibro() + " no existe.");
        }

        // 3. Validar existencia de la Categoría
        if (!categoriaRepository.existsById(request.getIdCategoria().intValue())) { 
            // Nota: ID Categoria es Integer, ID Libro es Long. Cuidado con los tipos.
            throw new BusinessException("La Categoría con ID " + request.getIdCategoria() + " no existe.");
        }

        // 4. Validar duplicados
        if (repo.existsByIdLibroAndIdCategoria(request.getIdLibro(), request.getIdCategoria())) {
            throw new BusinessException("El libro ya está asignado a esta categoría");
        }

        log.info("Asignando categoría {} al libro {}", request.getIdCategoria(), request.getIdLibro());

        LibroCategoria entity = modelMapper.map(request, LibroCategoria.class);
        LibroCategoria saved = repo.save(entity);

        return modelMapper.map(saved, LibroCategoriaResponse.class);
    }

    @Override
    @Transactional
    public LibroCategoriaResponse actualizar(Long id, LibroCategoriaRequest request) {
        LibroCategoria entity = repo.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Relación no encontrada"));

        // Validar si cambiamos el libro
        if (!entity.getIdLibro().equals(request.getIdLibro()) && !libroRepository.existsById(request.getIdLibro())) {
                 throw new BusinessException("El nuevo Libro ID no existe");
             }
        

        // Validar si cambiamos la categoría
        if (!entity.getIdCategoria().equals(request.getIdCategoria()) && !categoriaRepository.existsById(request.getIdCategoria().intValue())) {
                 throw new BusinessException("La nueva Categoría ID no existe");
             }
        

        // Validar duplicados si cambiaron los IDs
        boolean cambiaronIds = !entity.getIdLibro().equals(request.getIdLibro()) || 
                               !entity.getIdCategoria().equals(request.getIdCategoria());

        if (cambiaronIds && repo.existsByIdLibroAndIdCategoria(request.getIdLibro(), request.getIdCategoria())) {
             throw new BusinessException("Ya existe esta asignación Libro-Categoría");
        }

        modelMapper.map(request, entity);
        entity.setIdLibroCategoria(id); // Proteger ID

        LibroCategoria updated = repo.save(entity);
        log.info("Relación Libro-Categoría actualizada ID: {}", id);

        return modelMapper.map(updated, LibroCategoriaResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new BusinessException("No se puede eliminar: La relación no existe");
        }
        repo.deleteById(id);
        log.warn("Relación Libro-Categoría con ID {} eliminada", id);
    }
}