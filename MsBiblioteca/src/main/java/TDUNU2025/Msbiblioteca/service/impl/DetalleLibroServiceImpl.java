package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import TDUNU2025.Msbiblioteca.repository.DetalleLibroRepository;
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
                .orElseThrow(() -> new BusinessException("Detalle de libro no encontrado"));

        return modelMapper.map(detalle, DetalleLibroResponse.class);
    }

    @Override
    @Transactional
    public DetalleLibroResponse registrar(DetalleLibroRequest request) {
        validarStocks(request);

        if (repo.existsByIdLibro(request.getIdLibro())) {
            throw new BusinessException("El libro ya tiene un detalle de inventario registrado");
        }

        DetalleLibro entity = modelMapper.map(request, DetalleLibro.class);

        DetalleLibro saved = repo.save(entity);
        log.info("Detalle inventario creado para Libro ID: {}", saved.getIdLibro());

        return modelMapper.map(saved, DetalleLibroResponse.class);
    }

    @Override
    @Transactional
    public DetalleLibroResponse actualizar(Long id, DetalleLibroRequest request) {
        DetalleLibro detalle = repo.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Detalle no encontrado"));

        validarStocks(request);

        modelMapper.map(request, detalle);

        detalle.setIdDetalleLibro(id);

        DetalleLibro updated = repo.save(detalle);
        log.info("Stock actualizado para Detalle ID: {}", id);

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
        if (request.getStockTotal() != null && request.getStockTotal() < 0) {
            throw new BusinessException("El stock total no puede ser negativo");
        }
        if (request.getStockDisponible() != null && request.getStockDisponible() < 0) {
            throw new BusinessException("El stock disponible no puede ser negativo");
        }

        if (request.getStockTotal() != null && request.getStockDisponible() != null) {
            if (request.getStockDisponible() > request.getStockTotal()) {
                throw new BusinessException("El stock disponible no puede ser mayor al stock total");
            }
        }
    }
}