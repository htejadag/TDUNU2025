package com.unu.TDUNU2025.Msbiblioteca.service.impl;

import com.unu.TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import com.unu.TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import com.unu.TDUNU2025.Msbiblioteca.model.entity.Libro;
import com.unu.TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import com.unu.TDUNU2025.Msbiblioteca.repository.DetalleLibroRepository;
import com.unu.TDUNU2025.Msbiblioteca.service.DetalleLibroService;
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