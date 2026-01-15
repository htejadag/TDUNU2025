package com.pago.service.Imp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pago.model.entity.ClasificadorIngresoModel;
import com.pago.model.request.ClasificadorIngresoRequest;
import com.pago.model.response.ClasificadorIngresoResponse;
import com.pago.repository.ClasificadorIngresoRepository;
import com.pago.service.ClasificadorIngresoService;

@Slf4j
@Service
public class ClasificadorIngresoServiceImp implements ClasificadorIngresoService {

    @Autowired
    ClasificadorIngresoRepository clasificadorIngresoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ClasificadorIngresoResponse> listar() {
        log.info("[ClasificadorIngreso] listar - inicio");
        List<ClasificadorIngresoResponse> resp = clasificadorIngresoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, ClasificadorIngresoResponse.class))
                .toList();
        log.info("[ClasificadorIngreso] listar - fin | total={}", resp.size());
        return resp;
    }

    @Override
    public ClasificadorIngresoResponse obtenerPorId(Integer id) {
        log.info("[ClasificadorIngreso] obtenerPorId - id={}", id);
        ClasificadorIngresoResponse resp = clasificadorIngresoRepository.findById(id)
                .map(model -> modelMapper.map(model, ClasificadorIngresoResponse.class))
                .orElse(null);
        if (resp == null) {
            log.warn("[ClasificadorIngreso] obtenerPorId - no encontrado | id={}", id);
        } else {
            log.info("[ClasificadorIngreso] obtenerPorId - encontrado | id={}", id);
        }
        return resp;
    }

    @Override
    public ClasificadorIngresoResponse guardar(ClasificadorIngresoRequest request) {
        log.info("[ClasificadorIngreso] guardar - inicio");
        ClasificadorIngresoModel model = modelMapper.map(request, ClasificadorIngresoModel.class);
        ClasificadorIngresoModel saved = clasificadorIngresoRepository.save(model);
        log.info("[ClasificadorIngreso] guardar - ok | id={}", saved.getClasificadoringresoid());
        return modelMapper.map(saved, ClasificadorIngresoResponse.class);
    }

    @Override
    public ClasificadorIngresoResponse editar(Integer id, ClasificadorIngresoRequest request) {
        log.info("[ClasificadorIngreso] editar - inicio | id={}", id);
        ClasificadorIngresoModel existente = clasificadorIngresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe clasificador ingreso con ID: " + id));
        modelMapper.map(request, existente);
        ClasificadorIngresoModel actualizado = clasificadorIngresoRepository.save(existente);
        log.info("[ClasificadorIngreso] editar - ok | id={}", id);
        return modelMapper.map(actualizado, ClasificadorIngresoResponse.class);
    }

    @Override
    public void desactivar(Integer id) {
        log.info("[ClasificadorIngreso] desactivar - inicio | id={}", id);
        clasificadorIngresoRepository.desactivar(id);
        log.info("[ClasificadorIngreso] desactivar - ok | id={}", id);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("[ClasificadorIngreso] eliminar - inicio | id={}", id);
        clasificadorIngresoRepository.eliminar(id);
        log.info("[ClasificadorIngreso] eliminar - ok | id={}", id);
    }
}
