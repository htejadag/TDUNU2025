package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import TDUNU2025.Msbiblioteca.repository.DetalleLibroRepository;
import TDUNU2025.Msbiblioteca.service.DetalleLibroService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DetalleLibroServiceImpl implements DetalleLibroService {

    private final DetalleLibroRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<DetalleLibroResponse> listar() {
        return repo.findAll()
                .stream()
                .map(det -> modelMapper.map(det, DetalleLibroResponse.class))
                .toList();
    }

    @Override
    public DetalleLibroResponse obtener(Long id) {
        DetalleLibro detalle = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de libro no encontrado"));

        return modelMapper.map(detalle, DetalleLibroResponse.class);
    }

    @Override
    public DetalleLibroResponse registrar(DetalleLibroRequest request) {

        // Validación: un libro solo debe tener un detalle
        if (repo.existsByIdLibro(request.getIdLibro())) {
            throw new RuntimeException("El libro ya tiene un detalle registrado");
        }

        // Request → Entity
        DetalleLibro entity = modelMapper.map(request, DetalleLibro.class);

        // Guardar
        DetalleLibro saved = repo.save(entity);

        // Entity → Response
        return modelMapper.map(saved, DetalleLibroResponse.class);
    }

    @Override
    public DetalleLibroResponse actualizar(Long id, DetalleLibroRequest request) {

        DetalleLibro detalle = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de libro no encontrado"));

        // Actualiza SOLO campos proporcionados en el request
        modelMapper.map(request, detalle);

        DetalleLibro updated = repo.save(detalle);

        return modelMapper.map(updated, DetalleLibroResponse.class);
    }

    @Override
    public void eliminar(Long id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("El detalle de libro no existe");
        }

        repo.deleteById(id);
    }
}
