package tdunu2025.msbiblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import tdunu2025.msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.msbiblioteca.model.entity.Editorial;
import tdunu2025.msbiblioteca.model.request.EditorialRequest;
import tdunu2025.msbiblioteca.model.response.EditorialResponse;
import tdunu2025.msbiblioteca.repository.EditorialRepository;
import tdunu2025.msbiblioteca.service.EditorialService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EditorialServiceImpl implements EditorialService {

    private final EditorialRepository editorialRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<EditorialResponse> listar() {
        return editorialRepository.findAll().stream()
                .map(editorial -> modelMapper.map(editorial, EditorialResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public EditorialResponse obtener(Long id) {
        Editorial editorial = editorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editorial", "id", id));
        return modelMapper.map(editorial, EditorialResponse.class);
    }

    @Override
    public EditorialResponse registrar(EditorialRequest request) {
        // Validacion extra (opcional)
        if (editorialRepository.existsByNombre(request.getNombre())) {
             // Podrías lanzar una excepción personalizada aquí si quisieras
        }
        Editorial editorial = modelMapper.map(request, Editorial.class);
        Editorial saved = editorialRepository.save(editorial);
        return modelMapper.map(saved, EditorialResponse.class);
    }

    @Override
    public EditorialResponse actualizar(Long id, EditorialRequest request) {
        Editorial existing = editorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editorial", "id", id));

        modelMapper.map(request, existing);
        existing.setIdEditorial(id);

        Editorial updated = editorialRepository.save(existing);
        return modelMapper.map(updated, EditorialResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!editorialRepository.existsById(id)) {
            throw new ResourceNotFoundException("Editorial", "id", id);
        }
        editorialRepository.deleteById(id);
    }
}