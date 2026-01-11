package com.example.mscursos.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.mscursos.model.entity.CursoDetalleModel;
import com.example.mscursos.model.request.CursoDetalleRequest;
import com.example.mscursos.model.response.CursoDetalleResponse;
import com.example.mscursos.repository.CursoDetalleRepository;
import com.example.mscursos.service.CursoDetalleService;
import java.util.UUID;
import com.example.mscursos.dto.CursoDetalleEvent;
import com.example.mscursos.message.CursoDetallePublisher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoDetalleServiceImpl implements CursoDetalleService {

    // @Autowired
    private final CursoDetalleRepository cursoDetalleRepository;

    // @Autowired
    private final ModelMapper modelMapper;

    private final CursoDetallePublisher cursoDetallePublisher;

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

        CursoDetalleModel model = modelMapper.map(request, CursoDetalleModel.class);
        CursoDetalleModel saved = cursoDetalleRepository.save(model);

        // --- PUBLICAR EVENTO ---
        CursoDetalleEvent ev = new CursoDetalleEvent();
        ev.setEventId(UUID.randomUUID().toString());
        ev.setAction("CREATED");
        ev.setIdDetalleCurso(saved.getId());
        ev.setIdCurso(saved.getIdCurso());
        ev.setIdDocente(saved.getIdDocente());
        ev.setIdTipoCurso(saved.getIdTipoCurso());
        ev.setIdSemestre(saved.getIdSemestre());
        ev.setEstado(saved.getEstado());
        ev.setTimestamp(System.currentTimeMillis());

        // (opcional) si luego quieres, aquí enriqueces con cursoNombre/cursoCodigo
        cursoDetallePublisher.publish(ev);

        return modelMapper.map(saved, CursoDetalleResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        CursoDetalleModel cd = cursoDetalleRepository.findById(id).orElse(null);
        cursoDetalleRepository.deleteById(id);

        if (cd != null) {
            CursoDetalleEvent ev = new CursoDetalleEvent();
            ev.setEventId(UUID.randomUUID().toString());
            ev.setAction("DELETED");
            ev.setIdDetalleCurso(cd.getId());
            ev.setIdCurso(cd.getIdCurso());
            ev.setTimestamp(System.currentTimeMillis());
            cursoDetallePublisher.publish(ev);
        }
    }

}
