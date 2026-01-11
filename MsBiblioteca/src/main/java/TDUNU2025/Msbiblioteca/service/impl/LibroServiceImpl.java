package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.config.BusinessException; 
import TDUNU2025.Msbiblioteca.model.entity.Libro;
import TDUNU2025.Msbiblioteca.model.request.LibroRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroResponse;
import TDUNU2025.Msbiblioteca.repository.EditorialRepository; 
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

    private final LibroRepository libroRepository;
    private final EditorialRepository editorialRepository; 
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LibroResponse> listar() {
        return libroRepository.findAll()
                .stream()
                .map(libro -> modelMapper.map(libro, LibroResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LibroResponse obtener(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Libro no encontrado con ID: " + id));

        return modelMapper.map(libro, LibroResponse.class);
    }

    @Override
    @Transactional
    public LibroResponse registrar(LibroRequest request) {

        validarDatosLibro(request);

        // Validar ISBN único
        if (libroRepository.existsByIsbn(request.getIsbn())) {
            throw new BusinessException("El ISBN " + request.getIsbn() + " ya se encuentra registrado");
        }

        // Validar Existencia de Editorial (Integridad Referencial Manual)
        if (!editorialRepository.existsById(request.getIdEditorial())) {
            throw new BusinessException("La Editorial seleccionada (ID: " + request.getIdEditorial() + ") no existe");
        }


        Libro entity = modelMapper.map(request, Libro.class);
        Libro saved = libroRepository.save(entity);
        
        log.info("Libro registrado con éxito: {}", saved.getTitulo());

        return modelMapper.map(saved, LibroResponse.class);
    }

    @Override
    @Transactional
    public LibroResponse actualizar(Long id, LibroRequest request) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Libro no encontrado"));

        validarDatosLibro(request);

        // Validar que si cambia el ISBN, el nuevo no exista ya
        if (!libro.getIsbn().equals(request.getIsbn()) && libroRepository.existsByIsbn(request.getIsbn())) {
             throw new BusinessException("El nuevo ISBN ya pertenece a otro libro registrado");
        }

        // Validar Editorial al actualizar también
        if (!editorialRepository.existsById(request.getIdEditorial())) {
            throw new BusinessException("La Editorial seleccionada no existe");
        }

        modelMapper.map(request, libro);
        libro.setIdLibro(id); // Aseguramos el ID

        Libro updated = libroRepository.save(libro);
        log.info("Libro actualizado ID: {}", id);

        return modelMapper.map(updated, LibroResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new BusinessException("El libro no existe, no se puede eliminar");
        }
        libroRepository.deleteById(id);
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
        if (request.getIdEstadoLibro() == null || request.getIdEstadoLibro() <= 0) {
            throw new BusinessException("Debe seleccionar un estado válido para el libro");
        }
        if (request.getNumeroPaginas() != null && request.getNumeroPaginas() <= 0) {
            throw new BusinessException("El número de páginas debe ser mayor a 0");
        }
    }
}