package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import TDUNU2025.Msbiblioteca.model.entity.Prestamo;
import TDUNU2025.Msbiblioteca.model.request.PrestamoRequest;
import TDUNU2025.Msbiblioteca.model.response.PrestamoResponse;
import TDUNU2025.Msbiblioteca.repository.PrestamoRepository;
import TDUNU2025.Msbiblioteca.service.PrestamoService;
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