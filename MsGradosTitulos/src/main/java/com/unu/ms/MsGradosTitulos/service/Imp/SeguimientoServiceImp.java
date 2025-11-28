package com.unu.ms.MsGradosTitulos.service.Imp;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unu.ms.MsGradosTitulos.model.mapper.SeguimientoMapper;
import com.unu.ms.MsGradosTitulos.model.request.SeguimientoRequest;
import com.unu.ms.MsGradosTitulos.model.response.SeguimientoResponse;
import com.unu.ms.MsGradosTitulos.repository.SeguimientoRepository;
import com.unu.ms.MsGradosTitulos.service.SeguimientoService;

@Slf4j
@Service
public class SeguimientoServiceImp implements SeguimientoService {

    @Autowired
    SeguimientoRepository seguimientoRepository;

    @Autowired
    SeguimientoMapper mapper;

    @Override
    public List<SeguimientoResponse> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public SeguimientoResponse obtenerPorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public SeguimientoResponse crear(SeguimientoRequest seguimientoRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crear'");
    }

    @Override
    public void eliminar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public SeguimientoResponse actualizar(Integer id, SeguimientoRequest seguimientoActualizado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public boolean existePorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existePorId'");
    }

}
