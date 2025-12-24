package TDUNU2025.Msbiblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import TDUNU2025.Msbiblioteca.model.entity.Editorial;
import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import TDUNU2025.Msbiblioteca.repository.EditorialRepository;
import TDUNU2025.Msbiblioteca.service.EditorialService;

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
    }

    @Override
    public EditorialResponse actualizar(Long id, EditorialRequest request) {
        Editorial existingEditorial = findEditorialEntity(id);
        modelMapper.map(request, existingEditorial);
        Editorial updatedEditorial = editorialRepository.save(existingEditorial);
        return convertToResponse(updatedEditorial);
    }

    @Override
    public void eliminar(Long id) {
        Editorial editorial = findEditorialEntity(id);
        editorialRepository.delete(editorial);
    }
}