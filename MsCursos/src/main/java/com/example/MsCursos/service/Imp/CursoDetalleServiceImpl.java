package com.example.MsCursos.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsCursos.model.entity.CursoDetalleModel;
import com.example.MsCursos.model.request.CursoDetalleRequest;
import com.example.MsCursos.model.response.CursoDetalleResponse;
import com.example.MsCursos.repository.CursoDetalleRepository;
import com.example.MsCursos.service.CursoDetalleService;

@Service
public class CursoDetalleServiceImpl implements CursoDetalleService {

    @Autowired
    private CursoDetalleRepository cursoDetalleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CursoDetalleResponse> listar() {
        return cursoDetalleRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, CursoDetalleResponse.class))
                .toList();
    }

    @Override
    public CursoDetalleResponse obtenerPorId(Integer id) {
        return cursoDetalleRepository.findById(id)
                .map(model -> modelMapper.map(model, CursoDetalleResponse.class))
                .orElse(null);
    }

    @Override
    public CursoDetalleResponse guardar(CursoDetalleRequest request) {

        // 1. Request → Entity
        CursoDetalleModel model = modelMapper.map(request, CursoDetalleModel.class);

        // 2. Guardar
        CursoDetalleModel saved = cursoDetalleRepository.save(model);

        // 3. Entity → Response
        return modelMapper.map(saved, CursoDetalleResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        cursoDetalleRepository.deleteById(id);
    }
}
