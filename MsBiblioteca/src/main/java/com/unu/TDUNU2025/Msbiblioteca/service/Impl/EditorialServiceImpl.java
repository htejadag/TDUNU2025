package com.unu.TDUNU2025.Msbiblioteca.service.Impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Editorial;
import com.unu.TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import com.unu.TDUNU2025.Msbiblioteca.repository.EditorialRepository;
import com.unu.TDUNU2025.Msbiblioteca.service.EditorialService;

@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    EditorialRepository editorialRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<EditorialResponse> listar() {
        return editorialRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, EditorialResponse.class))
                .toList();
    }

    @Override
    public EditorialResponse obtenerPorId(Long id) {
        return editorialRepository.findById(id)
        .map(model -> modelMapper.map(model, EditorialResponse.class))
        .orElse(null);
  }

    @Override
    public EditorialResponse guardar(EditorialRequest editorial) {
    // 1. Convertir Request (DTO) a Entidad
        Editorial editorialEntity = modelMapper.map(editorial, Editorial.class);

        // 2. Guardar en Base de Datos
        Editorial editorialGuardada = editorialRepository.save(editorialEntity);

        // 3. Convertir Entidad guardada a Response (DTO) para devolver al cliente
        return modelMapper.map(editorialGuardada, EditorialResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        editorialRepository.deleteById(id);
    }

}