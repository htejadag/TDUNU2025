package tdunu2025.msbiblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import tdunu2025.msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.msbiblioteca.model.entity.CategoriaLibro;
import tdunu2025.msbiblioteca.model.request.CategoriaLibroRequest;
import tdunu2025.msbiblioteca.model.response.CategoriaLibroResponse;
import tdunu2025.msbiblioteca.repository.CategoriaLibroRepository;
import tdunu2025.msbiblioteca.service.CategoriaLibroService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaLibroServiceImpl implements CategoriaLibroService {

    private final CategoriaLibroRepository categoriaLibroRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoriaLibroResponse> listar() {
        return categoriaLibroRepository.findAll().stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaLibroResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaLibroResponse obtener(Long id) {
        CategoriaLibro categoria = categoriaLibroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaLibro", "id", id));
        return modelMapper.map(categoria, CategoriaLibroResponse.class);
    }

    @Override
    public CategoriaLibroResponse registrar(CategoriaLibroRequest request) {
        CategoriaLibro categoria = modelMapper.map(request, CategoriaLibro.class);
        CategoriaLibro saved = categoriaLibroRepository.save(categoria);
        return modelMapper.map(saved, CategoriaLibroResponse.class);
    }

    @Override
    public CategoriaLibroResponse actualizar(Long id, CategoriaLibroRequest request) {
        CategoriaLibro existingCategoria = categoriaLibroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaLibro", "id", id));

        modelMapper.map(request, existingCategoria);
        existingCategoria.setIdCategoria(id);
        
        CategoriaLibro updated = categoriaLibroRepository.save(existingCategoria);
        return modelMapper.map(updated, CategoriaLibroResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!categoriaLibroRepository.existsById(id)) {
            throw new ResourceNotFoundException("CategoriaLibro", "id", id);
        }
        categoriaLibroRepository.deleteById(id);
    }
}