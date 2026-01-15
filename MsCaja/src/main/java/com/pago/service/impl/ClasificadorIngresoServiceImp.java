package com.pago.service.impl;

import java.util.List;
<<<<<<< Updated upstream:MsCaja/src/main/java/com/pago/service/Imp/ClasificadorIngresoServiceImp.java
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
=======
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
>>>>>>> Stashed changes:MsCaja/src/main/java/com/pago/service/impl/ClasificadorIngresoServiceImp.java
import org.springframework.stereotype.Service;
import com.pago.model.entity.ClasificadorIngresoModel;
import com.pago.model.request.ClasificadorIngresoRequest;
import com.pago.model.response.ClasificadorIngresoResponse;
import com.pago.repository.ClasificadorIngresoRepository;
import com.pago.service.ClasificadorIngresoService;

<<<<<<< Updated upstream:MsCaja/src/main/java/com/pago/service/Imp/ClasificadorIngresoServiceImp.java
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
=======
@Service("clasificadorIngresoServicio")
@RequiredArgsConstructor
public class ClasificadorIngresoServiceImp implements ClasificadorIngresoService {
    @Qualifier("clasificadorIngresoRepositorio")
    private final ClasificadorIngresoRepository clasificadorIngresoRepositorio;

    @Override
    public List<ClasificadorIngresoModel> listarClasificadorIngreso() {
        return clasificadorIngresoRepositorio.findAll();
    }

    @Override
    public ClasificadorIngresoModel obtenerClasificadorIngreso(int id) {
        return clasificadorIngresoRepositorio.findById(id).orElse(null);
    }

    @Override
    public ClasificadorIngresoModel registrarClasificadorIngreso(ClasificadorIngresoModel clasificadorIngreso) {
        return clasificadorIngresoRepositorio.save(clasificadorIngreso);
    }

    @Override
    public ClasificadorIngresoModel actualizarClasificadorIngreso(ClasificadorIngresoModel clasificadorIngreso) {
        return clasificadorIngresoRepositorio.save(clasificadorIngreso);
    }

    @Override
    public void desactivarClasificadorIngreso(int id) {
        clasificadorIngresoRepositorio.desactivar(id);
    }

    @Override
    public void eliminarClasificadorIngreso(int id) {
        clasificadorIngresoRepositorio.eliminar(id);
>>>>>>> Stashed changes:MsCaja/src/main/java/com/pago/service/impl/ClasificadorIngresoServiceImp.java
    }
}
