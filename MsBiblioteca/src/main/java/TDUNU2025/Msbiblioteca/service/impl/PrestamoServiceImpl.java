package tdunu2025.msbiblioteca.service.impl;

import tdunu2025.msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.msbiblioteca.model.entity.Prestamo;
import tdunu2025.msbiblioteca.model.request.PrestamoRequest;
import tdunu2025.msbiblioteca.model.response.PrestamoResponse;
import tdunu2025.msbiblioteca.repository.PrestamoRepository;
import tdunu2025.msbiblioteca.service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PrestamoResponse> listar() {
        return prestamoRepository.findAll().stream()
                .map(prestamo -> modelMapper.map(prestamo, PrestamoResponse.class))
                .toList();
    }

    @Override
    public PrestamoResponse obtener(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo", "id", id));
        return modelMapper.map(prestamo, PrestamoResponse.class);
    }

    @Override
    public PrestamoResponse registrar(PrestamoRequest request) {
        // Aquí podrías validar stock o multas pendientes antes de guardar
        Prestamo prestamo = modelMapper.map(request, Prestamo.class);
        Prestamo saved = prestamoRepository.save(prestamo);
        return modelMapper.map(saved, PrestamoResponse.class);
    }

    @Override
    public PrestamoResponse actualizar(Long id, PrestamoRequest request) {
        Prestamo existing = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo", "id", id));
        
        modelMapper.map(request, existing);
        existing.setIdPrestamo(id);
        
        Prestamo updated = prestamoRepository.save(existing);
        return modelMapper.map(updated, PrestamoResponse.class);
    }


    @Override
    public void eliminar(Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prestamo", "id", id);
        }
        prestamoRepository.deleteById(id);
    }

    @Override
    public List<PrestamoResponse> listarporUsuario(Long idUsuario) {
        return prestamoRepository.findByIdUsuario(idUsuario)
        .stream()
        .map(p -> modelMapper.map(p, PrestamoResponse.class))
        .toList();
    }

    @Override
    public void registrarDevolucion(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
        .orElseThrow(() -> new ResourceNotFoundException("Prestamo", "id", idPrestamo));    
        if (prestamo.getFechaDevolucion() != null){
            throw new RuntimeException("el prestamo ya fue devuelto anteriormente.");            
        }
        prestamo.setFechaDevolucion(LocalDateTime.now());

        prestamo.setIdEstadoPrestamo(2L);

        prestamoRepository.save(prestamo);

    }
}