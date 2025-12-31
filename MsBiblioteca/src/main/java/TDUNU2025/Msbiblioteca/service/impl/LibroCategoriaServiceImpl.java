package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.LibroCategoria;
import TDUNU2025.Msbiblioteca.model.request.LibroCategoriaRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroCategoriaResponse;
import TDUNU2025.Msbiblioteca.repository.LibroCategoriaRepository;
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
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LibroCategoriaResponse> listar() {
        return repo.findAll()
                .stream()
                .map(item -> modelMapper.map(item, LibroCategoriaResponse.class))
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

        
        modelMapper.map(request, entity); 

        entity.setIdLibroCategoria(id); 

        LibroCategoria updated = repo.save(entity);
        log.info("Relación Libro-Categoría actualizada con ID: {}", id);

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