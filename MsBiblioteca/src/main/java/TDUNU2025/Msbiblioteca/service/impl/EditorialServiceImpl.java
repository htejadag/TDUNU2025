package TDUNU2025.Msbiblioteca.service.impl;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
=======
>>>>>>> origin/origin/jordinTrujillo
import TDUNU2025.Msbiblioteca.model.entity.Editorial;
import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import TDUNU2025.Msbiblioteca.repository.EditorialRepository;
import TDUNU2025.Msbiblioteca.service.EditorialService;

<<<<<<< HEAD
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor 
public class EditorialServiceImpl implements EditorialService {

    private final EditorialRepository editorialRepository;
    private final ModelMapper modelMapper;
    private Editorial findEditorialEntity(Long id) {
        return editorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editorial", "idEditorial", id));
    }
    private EditorialResponse convertToResponse(Editorial editorial) {
        return modelMapper.map(editorial, EditorialResponse.class);
    }
    @Override
    public List<EditorialResponse> listar() {
        return editorialRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EditorialResponse obtenerPorId(Long id) {
        Editorial editorial = findEditorialEntity(id);
        return convertToResponse(editorial);
    }

    @Override
    public EditorialResponse guardar(EditorialRequest request) {
        Editorial editorialEntity = modelMapper.map(request, Editorial.class);
        Editorial editorialGuardada = editorialRepository.save(editorialEntity);
        return convertToResponse(editorialGuardada);
=======
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorialServiceImpl implements EditorialService {

    private final EditorialRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public EditorialResponse guardar(EditorialRequest request) {

        if (repo.existsByNombre(request.getNombre())) {
            throw new RuntimeException("Ya existe una editorial con ese nombre");
        }

        // Request → Entity
        Editorial entity = modelMapper.map(request, Editorial.class);

        // Guardar
        Editorial saved = repo.save(entity);

        // Entity → Response
        return modelMapper.map(saved, EditorialResponse.class);
    }

    @Override
    public List<EditorialResponse> listar() {
        return repo.findAll()
                .stream()
                .map(editorial -> modelMapper.map(editorial, EditorialResponse.class))
                .toList();
    }

    @Override
    public EditorialResponse buscarPorId(Long id) {
        Editorial editorial = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));

        return modelMapper.map(editorial, EditorialResponse.class);
>>>>>>> origin/origin/jordinTrujillo
    }

    @Override
    public EditorialResponse actualizar(Long id, EditorialRequest request) {
<<<<<<< HEAD
        Editorial existingEditorial = findEditorialEntity(id);
        modelMapper.map(request, existingEditorial);
        Editorial updatedEditorial = editorialRepository.save(existingEditorial);
        return convertToResponse(updatedEditorial);
=======

        Editorial editorial = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));

        // Actualizar con ModelMapper (sobrescribe solo los campos del request)
        modelMapper.map(request, editorial);

        Editorial updated = repo.save(editorial);

        return modelMapper.map(updated, EditorialResponse.class);
>>>>>>> origin/origin/jordinTrujillo
    }

    @Override
    public void eliminar(Long id) {
<<<<<<< HEAD
        Editorial editorial = findEditorialEntity(id);
        editorialRepository.delete(editorial);
    }
}
=======
        repo.deleteById(id);
    }
}
>>>>>>> origin/origin/jordinTrujillo
