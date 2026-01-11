package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.config.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import TDUNU2025.Msbiblioteca.repository.DetalleLibroRepository;
import TDUNU2025.Msbiblioteca.repository.LibroRepository; 
import TDUNU2025.Msbiblioteca.service.DetalleLibroService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DetalleLibroServiceImpl implements DetalleLibroService {

    private final DetalleLibroRepository repo;
    private final LibroRepository libroRepository; 
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DetalleLibroResponse> listar() {
        return repo.findAll()
                .stream()
                .map(det -> modelMapper.map(det, DetalleLibroResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleLibroResponse obtener(Long id) {
        DetalleLibro detalle = repo.findById(id)
                .orElseThrow(() -> new BusinessException("Detalle de libro no encontrado con ID: " + id));

        return modelMapper.map(detalle, DetalleLibroResponse.class);
    }

    @Override
    @Transactional
    public DetalleLibroResponse registrar(DetalleLibroRequest request) {
        // 1. Validar Stocks (Negativos, coherencia)
        validarStocks(request);

        // 2. Validar que el libro exista (Integridad Referencial)
        if (!libroRepository.existsById(request.getIdLibro())) {
            throw new BusinessException("El Libro con ID " + request.getIdLibro() + " no existe.");
        }

        // 3. Validar que no exista ya un inventario para este libro (1 a 1)
        if (repo.existsByIdLibro(request.getIdLibro())) {
            throw new BusinessException("El libro ID " + request.getIdLibro() + " ya tiene un registro de inventario.");
        }

        DetalleLibro entity = modelMapper.map(request, DetalleLibro.class);
        
        // Asignar stock disponible igual al total si viene nulo
        if (entity.getStockDisponible() == null) {
            entity.setStockDisponible(entity.getStockTotal());
        }

        DetalleLibro saved = repo.save(entity);
        log.info("Inventario creado para Libro ID: {}", saved.getIdLibro());

        return modelMapper.map(saved, DetalleLibroResponse.class);
    }

    @Override
    @Transactional
    public DetalleLibroResponse actualizar(Long id, DetalleLibroRequest request) {
        DetalleLibro detalle = repo.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Detalle no encontrado"));

        validarStocks(request);

        // Si intenta cambiar el ID del libro, validamos que el nuevo exista
        if (!detalle.getIdLibro().equals(request.getIdLibro())) {
             if (!libroRepository.existsById(request.getIdLibro())) {
                 throw new BusinessException("El nuevo Libro ID no existe");
             }
             // Y verificamos que el nuevo libro no tenga ya inventario
             if (repo.existsByIdLibro(request.getIdLibro())) {
                 throw new BusinessException("El nuevo Libro ID ya tiene inventario asignado");
             }
        }

        modelMapper.map(request, detalle);
        detalle.setIdDetalleLibro(id); // Proteger ID

        DetalleLibro updated = repo.save(detalle);
        log.info("Stock actualizado ID: {}", id);

        return modelMapper.map(updated, DetalleLibroResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new BusinessException("El detalle de libro no existe");
        }
        repo.deleteById(id);
        log.warn("Detalle de libro eliminado ID: {}", id);
    }

    private void validarStocks(DetalleLibroRequest request) {
        if (request.getIdLibro() == null) {
             throw new BusinessException("Debe especificar el ID del libro");
        }
        if (request.getStockTotal() != null && request.getStockTotal() < 0) {
            throw new BusinessException("El stock total no puede ser negativo");
        }
        if (request.getStockDisponible() != null && request.getStockDisponible() < 0) {
            throw new BusinessException("El stock disponible no puede ser negativo");
        }
        if (request.getStockTotal() != null && request.getStockDisponible() != null && 
            request.getStockDisponible() > request.getStockTotal()) {
                throw new BusinessException("El stock disponible no puede ser mayor al stock total");
        }
    }
}