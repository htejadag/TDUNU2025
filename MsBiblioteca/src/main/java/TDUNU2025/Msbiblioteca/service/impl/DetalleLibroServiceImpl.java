package TDUNU2025.Msbiblioteca.service.impl;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import TDUNU2025.Msbiblioteca.model.entity.Libro;
=======
import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
>>>>>>> origin/origin/jordinTrujillo
import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import TDUNU2025.Msbiblioteca.repository.DetalleLibroRepository;
import TDUNU2025.Msbiblioteca.service.DetalleLibroService;

<<<<<<< HEAD
import java.util.List;
import java.util.stream.Collectors;

=======
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
>>>>>>> origin/origin/jordinTrujillo
@Service
@RequiredArgsConstructor
public class DetalleLibroServiceImpl implements DetalleLibroService {

<<<<<<< HEAD
    private final DetalleLibroRepository detalleLibroRepository;
    private final ModelMapper modelMapper;
    
    private DetalleLibro findDetalleLibro(Integer id) {
        return detalleLibroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleLibro", "idDetalleLibro", id));
    }

    private DetalleLibroResponse convertToResponse(DetalleLibro detalleLibro) {
        DetalleLibroResponse response = modelMapper.map(detalleLibro, DetalleLibroResponse.class);
        response.setIdLibro(detalleLibro.getLibro().getIdLibro());         
        return response;
    }

    @Override
    public DetalleLibroResponse save(DetalleLibroRequest request) {
        DetalleLibro detalleLibro = modelMapper.map(request, DetalleLibro.class);
        detalleLibro.setLibro(Libro.builder().idLibro(request.getIdLibro()).build());        
        //detalleLibro.setLibro(new Libro(request.getIdLibro()));
        DetalleLibro savedDetalle = detalleLibroRepository.save(detalleLibro);        
        return convertToResponse(savedDetalle);
    }

    @Override
    public DetalleLibroResponse findById(Integer idDetalleLibro) {
        DetalleLibro detalleLibro = findDetalleLibro(idDetalleLibro);
        return convertToResponse(detalleLibro);
    }

    @Override
    public List<DetalleLibroResponse> findAll() {
        return detalleLibroRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DetalleLibroResponse update(Integer idDetalleLibro, DetalleLibroRequest request) {
        DetalleLibro existingDetalle = findDetalleLibro(idDetalleLibro);
        modelMapper.map(request, existingDetalle); 
        existingDetalle.setLibro(Libro.builder().idLibro(request.getIdLibro()).build());        
        DetalleLibro updatedDetalle = detalleLibroRepository.save(existingDetalle);        
        return convertToResponse(updatedDetalle);
    }

    @Override
    public void delete(Integer idDetalleLibro) {
        DetalleLibro detalleLibro = findDetalleLibro(idDetalleLibro);
        detalleLibroRepository.delete(detalleLibro);
    }
}
=======
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
>>>>>>> origin/origin/jordinTrujillo
