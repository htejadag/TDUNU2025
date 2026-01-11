package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import TDUNU2025.Msbiblioteca.repository.DetalleLibroRepository;
import TDUNU2025.Msbiblioteca.service.DetalleLibroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetalleLibroServiceImpl implements DetalleLibroService {

    private final DetalleLibroRepository detalleLibroRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DetalleLibroResponse> listar() {
        return detalleLibroRepository.findAll().stream()
                .map(detalle -> modelMapper.map(detalle, DetalleLibroResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public DetalleLibroResponse obtener(Long id) {
        DetalleLibro detalle = detalleLibroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleLibro", "id", id));
        return modelMapper.map(detalle, DetalleLibroResponse.class);
    }

    @Override
    public DetalleLibroResponse registrar(DetalleLibroRequest request) {
        // Validación opcional: Verificar si el libro existe antes (se hará con el mapper o manual)
        DetalleLibro detalle = modelMapper.map(request, DetalleLibro.class);
        DetalleLibro saved = detalleLibroRepository.save(detalle);
        return modelMapper.map(saved, DetalleLibroResponse.class);
    }

    @Override
    public DetalleLibroResponse actualizar(Long id, DetalleLibroRequest request) {
        DetalleLibro existingDetalle = detalleLibroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleLibro", "id", id));

        modelMapper.map(request, existingDetalle);
        existingDetalle.setIdDetalleLibro(id);

        DetalleLibro updated = detalleLibroRepository.save(existingDetalle);
        return modelMapper.map(updated, DetalleLibroResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!detalleLibroRepository.existsById(id)) {
            throw new ResourceNotFoundException("DetalleLibro", "id", id);
        }
        detalleLibroRepository.deleteById(id);
    }
}