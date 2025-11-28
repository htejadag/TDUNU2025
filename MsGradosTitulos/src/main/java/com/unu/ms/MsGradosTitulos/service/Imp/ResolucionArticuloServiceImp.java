package com.unu.ms.MsGradosTitulos.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unu.ms.MsGradosTitulos.model.mapper.ResolucionArticuloMapper;
import com.unu.ms.MsGradosTitulos.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionArticuloResponse;
import com.unu.ms.MsGradosTitulos.repository.ResolucionArticuloRepository;
import com.unu.ms.MsGradosTitulos.service.ResolucionArticuloService;

@Slf4j
@Service
public class ResolucionArticuloServiceImp implements ResolucionArticuloService {

    @Autowired
    ResolucionArticuloRepository resolucionArticuloRepository;

    @Autowired
    ResolucionArticuloMapper mapper;

    @Override
    public List<ResolucionArticuloResponse> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public ResolucionArticuloResponse obtenerPorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public ResolucionArticuloResponse crear(ResolucionArticuloRequest resolucionArticuloRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crear'");
    }

    @Override
    public void eliminar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public ResolucionArticuloResponse actualizar(Integer id, ResolucionArticuloRequest resolucionArticuloActualizado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public boolean existePorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existePorId'");
    }

}
