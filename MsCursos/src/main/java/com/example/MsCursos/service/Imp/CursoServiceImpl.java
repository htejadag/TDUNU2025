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
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<CursoResponse> listarPorCarrera(Integer idCarrera) {
        return cursoRepository.findByIdCarrera(idCarrera)
                .stream()
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .toList();
    }

    @Override
    public List<CursoResponse> listarPorEstado(Boolean estado) {
        return cursoRepository.findByEstado(estado)
                .stream()
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .toList();
    }

    @Override
    public List<CursoResponse> listarPorCarreraYEstado(Integer idCarrera, Boolean estado) {
        return cursoRepository.findByIdCarreraAndEstado(idCarrera, estado)
                .stream()
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .toList();
    }

    @Override
    @Transactional
    public void upsertDesdeKafka(CursoRequest req) {

        CursoModel model;

        // Si viene ID: intenta actualizar; si no existe, crea nuevo
        if (req.getId() != null) {
            model = cursoRepository.findById(req.getId()).orElse(new CursoModel());
            model.setId(req.getId()); // por si era nuevo
        } else {
            model = new CursoModel();
        }

        // Map DTO -> Entity
        model.setNombre(req.getNombre());
        model.setCodigo(req.getCodigo());
        model.setIdCiclo(req.getIdCiclo());
        model.setIdCarrera(req.getIdCarrera());
        model.setCreditos(req.getCreditos());
        model.setHorasTeoricas(req.getHorasTeoricas());
        model.setHorasPracticas(req.getHorasPracticas());
        model.setEstado(req.getEstado() != null ? req.getEstado() : true);

        cursoRepository.save(model);
    }
}
