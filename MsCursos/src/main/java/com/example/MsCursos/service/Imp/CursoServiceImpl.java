package com.example.MsCursos.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsCursos.model.entity.CursoModel;
import com.example.MsCursos.model.request.CursoRequest;
import com.example.MsCursos.model.response.CursoResponse;
import com.example.MsCursos.repository.CursoRepository;
import com.example.MsCursos.service.CursoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CursoResponse> listar() {
        return cursoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .toList();
    }

    @Override
    public CursoResponse obtenerPorId(Integer id) {
        return cursoRepository.findById(id)
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .orElse(null);
    }

    @Override
    public CursoResponse guardar(CursoRequest request) {

        // 1. Request -> Entity
        CursoModel model = modelMapper.map(request, CursoModel.class);

        // 2. Guardar en la BD
        CursoModel saved = cursoRepository.save(model);

        // 3. Entity -> Response
        return modelMapper.map(saved, CursoResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        cursoRepository.deleteById(id);
    }
}
