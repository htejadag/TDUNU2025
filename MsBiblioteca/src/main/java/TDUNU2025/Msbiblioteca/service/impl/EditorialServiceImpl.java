package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.model.entity.Editorial;
import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import TDUNU2025.Msbiblioteca.repository.EditorialRepository;
import TDUNU2025.Msbiblioteca.service.EditorialService;

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
    }

    @Override
    public EditorialResponse actualizar(Long id, EditorialRequest request) {

        Editorial editorial = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));

        // Actualizar con ModelMapper (sobrescribe solo los campos del request)
        modelMapper.map(request, editorial);

        Editorial updated = repo.save(editorial);

        return modelMapper.map(updated, EditorialResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
