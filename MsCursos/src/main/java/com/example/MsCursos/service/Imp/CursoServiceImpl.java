package com.example.mscursos.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.mscursos.model.entity.CursoModel;
import com.example.mscursos.model.request.CursoRequest;
import com.example.mscursos.model.response.CursoResponse;
import com.example.mscursos.repository.CursoRepository;
import com.example.mscursos.service.CursoService;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    private static final String CACHE_CURSOS = "cursos";

    // @Autowired
    private final CursoRepository cursoRepository;

    // @Autowired
    private final ModelMapper modelMapper;

    @Override
    @Cacheable(cacheNames = CACHE_CURSOS, key = "'listar'")
    public List<CursoResponse> listar() {
        return cursoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(cacheNames = CACHE_CURSOS, key = "'id:' + #id")
    public CursoResponse obtenerPorId(Integer id) {
        return cursoRepository.findById(id)
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .orElse(null);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = CACHE_CURSOS, allEntries = true)
    public CursoResponse guardar(CursoRequest request) {

        // 1. Request -> Entity
        CursoModel model = modelMapper.map(request, CursoModel.class);

        // 2. Guardar en la BD
        CursoModel saved = cursoRepository.save(model);

        // 3. Entity -> Response
        return modelMapper.map(saved, CursoResponse.class);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = CACHE_CURSOS, allEntries = true)
    public void eliminar(Integer id) {
        cursoRepository.deleteById(id);
    }

    @Override
    @Cacheable(cacheNames = CACHE_CURSOS, key = "'carrera:' + #idCarrera")
    public List<CursoResponse> listarPorCarrera(Integer idCarrera) {
        return cursoRepository.findByIdCarrera(idCarrera)
                .stream()
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(cacheNames = CACHE_CURSOS, key = "'estado:' + #estado")
    public List<CursoResponse> listarPorEstado(Boolean estado) {
        return cursoRepository.findByEstado(estado)
                .stream()
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .toList();
    }

    @Override
    @Cacheable(cacheNames = CACHE_CURSOS, key = "'carrera:' + #idCarrera + ':estado:' + #estado")
    public List<CursoResponse> listarPorCarreraYEstado(Integer idCarrera, Boolean estado) {
        return cursoRepository.findByIdCarreraAndEstado(idCarrera, estado)
                .stream()
                .map(model -> modelMapper.map(model, CursoResponse.class))
                .toList();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = CACHE_CURSOS, allEntries = true) // viene data nueva por kafka
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
        model.setEstado(req.getEstado() == null || req.getEstado());

        cursoRepository.save(model);
    }
}
