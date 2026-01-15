package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.config.BusinessException; 
import TDUNU2025.Msbiblioteca.model.entity.Editorial;
import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import TDUNU2025.Msbiblioteca.repository.EditorialRepository;
import TDUNU2025.Msbiblioteca.service.EditorialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditorialServiceImpl implements EditorialService {

    private final EditorialRepository editorialRepository; 
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EditorialResponse> listar() {
        return editorialRepository.findAll()
                .stream()
                .map(editorial -> modelMapper.map(editorial, EditorialResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EditorialResponse buscarPorId(Long id) {
        Editorial editorial = editorialRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Editorial no encontrada con ID: " + id));

        return modelMapper.map(editorial, EditorialResponse.class);
    }

    @Override
    @Transactional
    public EditorialResponse guardar(EditorialRequest request) {
        validarDatosEditorial(request);

        if (editorialRepository.existsByNombre(request.getNombre())) {
            throw new BusinessException("Ya existe una editorial con el nombre: " + request.getNombre());
        }

        Editorial entity = modelMapper.map(request, Editorial.class);
        Editorial saved = editorialRepository.save(entity);

        log.info("Editorial guardada: {}", saved.getNombre());
        return modelMapper.map(saved, EditorialResponse.class);
    }

    @Override
    @Transactional
    public EditorialResponse actualizar(Long id, EditorialRequest request) {
        Editorial editorial = editorialRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Editorial no encontrada"));

        validarDatosEditorial(request);

        if (!editorial.getNombre().equalsIgnoreCase(request.getNombre()) &&
                editorialRepository.existsByNombre(request.getNombre())) {
            throw new BusinessException("El nuevo nombre ya pertenece a otra editorial");
        }

        modelMapper.map(request, editorial);
        editorial.setIdEditorial(id); 

        Editorial updated = editorialRepository.save(editorial);
        log.info("Editorial actualizada ID: {}", id);

        return modelMapper.map(updated, EditorialResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!editorialRepository.existsById(id)) {
            throw new BusinessException("La editorial no existe, no se puede eliminar");
        }
        editorialRepository.deleteById(id);
        log.warn("Editorial eliminada ID: {}", id);
    }

    private void validarDatosEditorial(EditorialRequest request) {
        if (request == null) throw new BusinessException("El request es nulo");
        if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
            throw new BusinessException("El nombre de la editorial es obligatorio");
        }
    }
}