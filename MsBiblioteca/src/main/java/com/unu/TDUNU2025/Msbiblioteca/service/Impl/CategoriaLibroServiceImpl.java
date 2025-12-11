package com.unu.TDUNU2025.Msbiblioteca.service.impl;

import com.unu.TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import com.unu.TDUNU2025.Msbiblioteca.model.entity.CategoriaLibro;
import com.unu.TDUNU2025.Msbiblioteca.model.request.CategoriaLibroRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.CategoriaLibroResponse;
import com.unu.TDUNU2025.Msbiblioteca.repository.CategoriaLibroRepository;
import com.unu.TDUNU2025.Msbiblioteca.service.CategoriaLibroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaLibroServiceImpl implements CategoriaLibroService {

    private final CategoriaLibroRepository categoriaLibroRepository;
    private final ModelMapper modelMapper;

    private CategoriaLibro findCategoriaLibroEntity(Long id) {
        return categoriaLibroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaLibro", "idCategoria", id));
    }

    private CategoriaLibroResponse convertToResponse(CategoriaLibro categoria) {
        return modelMapper.map(categoria, CategoriaLibroResponse.class);
    }

    @Override
    public CategoriaLibroResponse save(CategoriaLibroRequest request) {
        CategoriaLibro categoriaLibro = modelMapper.map(request, CategoriaLibro.class);
        CategoriaLibro savedCategoriaLibro = categoriaLibroRepository.save(categoriaLibro);
        return convertToResponse(savedCategoriaLibro);
    }

    @Override
    public CategoriaLibroResponse findById(Long idCategoria) {
        CategoriaLibro categoriaLibro = findCategoriaLibroEntity(idCategoria);
        return convertToResponse(categoriaLibro);
    }

    @Override
    public List<CategoriaLibroResponse> findAll() {
        return categoriaLibroRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaLibroResponse update(Long idCategoria, CategoriaLibroRequest request) {
        CategoriaLibro existingCategoria = findCategoriaLibroEntity(idCategoria);

        modelMapper.map(request, existingCategoria); 
        
        CategoriaLibro updatedCategoria = categoriaLibroRepository.save(existingCategoria);
        return convertToResponse(updatedCategoria);
    }

    @Override
    public void delete(Long idCategoria) {
        CategoriaLibro categoriaLibro = findCategoriaLibroEntity(idCategoria);
        categoriaLibroRepository.delete(categoriaLibro);
    }
}