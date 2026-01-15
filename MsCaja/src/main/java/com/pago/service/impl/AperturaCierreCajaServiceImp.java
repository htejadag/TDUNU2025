package com.pago.service.impl;

import java.util.List;
<<<<<<< Updated upstream:MsCaja/src/main/java/com/pago/service/Imp/AperturaCierreCajaServiceImp.java
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
=======
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
>>>>>>> Stashed changes:MsCaja/src/main/java/com/pago/service/impl/AperturaCierreCajaServiceImp.java
import org.springframework.stereotype.Service;
import com.pago.model.entity.AperturaCierreCajaModel;
import com.pago.model.request.AperturaCierreCajaRequest;
import com.pago.model.response.AperturaCierreCajaResponse;
import com.pago.repository.AperturaCierreCajaRepository;
import com.pago.service.AperturaCierreCajaService;

<<<<<<< Updated upstream:MsCaja/src/main/java/com/pago/service/Imp/AperturaCierreCajaServiceImp.java
@Slf4j
@Service
public class AperturaCierreCajaServiceImp implements AperturaCierreCajaService {

    @Autowired
    AperturaCierreCajaRepository aperturaCierreCajaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AperturaCierreCajaResponse> listar() {
        log.info("[AperturaCierreCaja] listar - inicio");
        List<AperturaCierreCajaResponse> resp = aperturaCierreCajaRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, AperturaCierreCajaResponse.class))
                .toList();
        log.info("[AperturaCierreCaja] listar - fin | total={}", resp.size());
        return resp;
    }

    @Override
    public AperturaCierreCajaResponse obtenerPorId(Integer id) {
        log.info("[AperturaCierreCaja] obtenerPorId - id={}", id);
        AperturaCierreCajaResponse resp = aperturaCierreCajaRepository.findById(id)
                .map(model -> modelMapper.map(model, AperturaCierreCajaResponse.class))
                .orElse(null);
        if (resp == null) {
            log.warn("[AperturaCierreCaja] obtenerPorId - no encontrado | id={}", id);
        } else {
            log.info("[AperturaCierreCaja] obtenerPorId - encontrado | id={}", id);
        }
        return resp;
    }

    @Override
    public AperturaCierreCajaResponse guardar(AperturaCierreCajaRequest request) {
        AperturaCierreCajaModel model = modelMapper.map(request, AperturaCierreCajaModel.class);
        AperturaCierreCajaModel saved = aperturaCierreCajaRepository.save(model);
        log.info("[AperturaCierreCaja] guardar - ok | id={}", saved.getAperturacierrecajaid());
        AperturaCierreCajaResponse response = modelMapper.map(saved, AperturaCierreCajaResponse.class);
        //return modelMapper.map(saved, AperturaCierreCajaResponse.class);
        return response;
    }

    @Override
    public AperturaCierreCajaResponse editar(Integer id, AperturaCierreCajaRequest request) {
        log.info("[AperturaCierreCaja] editar - inicio | id={}", id);
        AperturaCierreCajaModel existente = aperturaCierreCajaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe apertura/cierre de caja con ID: " + id));

        modelMapper.map(request, existente);
        AperturaCierreCajaModel actualizado = aperturaCierreCajaRepository.save(existente);
        log.info("[AperturaCierreCaja] editar - ok | id={}", id);
        return modelMapper.map(actualizado, AperturaCierreCajaResponse.class);
    }

    @Override
    public void desactivar(Integer id) {
        log.info("[AperturaCierreCaja] desactivar - inicio | id={}", id);
        aperturaCierreCajaRepository.desactivar(id);
        log.info("[AperturaCierreCaja] desactivar - ok | id={}", id);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("[AperturaCierreCaja] eliminar - inicio | id={}", id);
        aperturaCierreCajaRepository.eliminar(id);
        log.info("[AperturaCierreCaja] eliminar - ok | id={}", id);
    }
=======
@Service("aperturaCierreCajaServicio")
@RequiredArgsConstructor
public class AperturaCierreCajaServiceImp implements AperturaCierreCajaService {
    @Qualifier("aperturaCierreCajaRepositorio")
    private final AperturaCierreCajaRepository apeCieCajaRepositorio;

    @Override
    public List<AperturaCierreCajaModel> listarApeCieCaja() {
        return apeCieCajaRepositorio.findAll();
    }

    @Override
    public AperturaCierreCajaModel obtenerApeCieCaja(int id) {
        return apeCieCajaRepositorio.findById(id).orElse(null);
    }

    @Override
    public AperturaCierreCajaModel registrarApeCieCaja(AperturaCierreCajaModel apeCieCaja) {
        return apeCieCajaRepositorio.save(apeCieCaja);
    }

    @Override
    public AperturaCierreCajaModel actualizarApeCieCaja(AperturaCierreCajaModel apeCieCaja) {
        return apeCieCajaRepositorio.save(apeCieCaja);
    }

    @Override
    public void desactivarApeCieCaja(int id) {
        apeCieCajaRepositorio.desactivar(id);
    }

    @Override
    public void eliminarApeCieCaja(int id) {
        apeCieCajaRepositorio.eliminar(id);
    }

>>>>>>> Stashed changes:MsCaja/src/main/java/com/pago/service/impl/AperturaCierreCajaServiceImp.java
}
