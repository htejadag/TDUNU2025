package com.example.mscursos.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.mscursos.model.entity.CursoDetalleModel;
import com.example.mscursos.model.request.CursoDetalleRequest;
import com.example.mscursos.model.response.CursoDetalleResponse;
import com.example.mscursos.repository.CursoDetalleRepository;
import com.example.mscursos.service.CursoDetalleService;

import jakarta.transaction.Transactional;

import java.util.UUID;
import com.example.mscursos.dto.CursoDetalleEvent;
import com.example.mscursos.message.CursoDetallePublisher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CursoDetalleServiceImpl implements CursoDetalleService {

    private final CursoDetalleRepository cursoDetalleRepository;

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

        boolean existe = cursoDetalleRepository
                .existsByIdCursoAndIdDocenteAndIdSemestreAndIdTipoCursoAndEstadoTrue(
                        request.getIdCurso(),
                        request.getIdDocente(),
                        request.getIdSemestre(),
                        request.getIdTipoCurso());

        if (existe) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe una asignación activa de este curso, tipo, docente y semestre");
        }

        CursoDetalleModel model = modelMapper.map(request, CursoDetalleModel.class);
        CursoDetalleModel saved = cursoDetalleRepository.save(model);

        CursoDetalleEvent ev = new CursoDetalleEvent();
        ev.setEventId(UUID.randomUUID().toString());
        ev.setAction("CREATED");
        ev.setIdDetalleCurso(saved.getId());
        ev.setIdCurso(saved.getIdCurso());
        ev.setIdSemestre(saved.getIdSemestre());
        ev.setEstado(saved.getEstado());
        ev.setTimestamp(System.currentTimeMillis());

        cursoDetallePublisher.publish(ev);

        return modelMapper.map(saved, CursoDetalleResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        // solo eliminar lógicamente
        CursoDetalleModel model = cursoDetalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CursoDetalle no encontrado"));

        model.setEstado(false);

        CursoDetalleModel updated = cursoDetalleRepository.save(model);
        CursoDetalleEvent ev = new CursoDetalleEvent();

        ev.setEventId(UUID.randomUUID().toString());
        ev.setAction("UPDATED");
        ev.setIdDetalleCurso(updated.getId());
        ev.setIdCurso(updated.getIdCurso());
        ev.setIdSemestre(updated.getIdSemestre());
        ev.setEstado(updated.getEstado());
        ev.setTimestamp(System.currentTimeMillis());

        cursoDetallePublisher.publish(ev);
    }

}
