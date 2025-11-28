package com.unu.ms.MsGradosTitulos.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unu.ms.MsGradosTitulos.model.mapper.ExpedienteMapper;
import com.unu.ms.MsGradosTitulos.model.request.ExpedienteRequest;
import com.unu.ms.MsGradosTitulos.model.response.ExpedienteResponse;
import com.unu.ms.MsGradosTitulos.repository.ExpedienteRepository;
import com.unu.ms.MsGradosTitulos.service.ExpedienteService;

@Slf4j
@Service
public class ExpedienteServiceImp implements ExpedienteService {

    @Autowired
    ExpedienteRepository expedienteRepository;

    @Autowired
    ExpedienteMapper mapper;

    @Override
    public List<ExpedienteResponse> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public ExpedienteResponse obtenerPorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public ExpedienteResponse crear(ExpedienteRequest expedienteRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crear'");
    }

    @Override
    public void eliminar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public ExpedienteResponse actualizar(Integer id, ExpedienteRequest expedienteActualizado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public boolean existePorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existePorId'");
    }

}
