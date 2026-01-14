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

import java.util.List;

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
}