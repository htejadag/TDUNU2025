package com.example.mscursos.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.mscursos.model.entity.CursoDetalleModel;
import com.example.mscursos.model.request.CursoDetalleRequest;
import com.example.mscursos.model.response.CursoDetalleResponse;
import com.example.mscursos.repository.CursoDetalleRepository;
import com.example.mscursos.service.CursoDetalleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoDetalleServiceImpl implements CursoDetalleService {

    // @Autowired
    private final CursoDetalleRepository cursoDetalleRepository;

    // @Autowired
    private final ModelMapper modelMapper;

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
